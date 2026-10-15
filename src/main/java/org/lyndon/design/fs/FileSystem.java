package org.lyndon.design.fs;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.lyndon.design.fs.Constants.SEP;

public class FileSystem {

    private static final int MAX_SYMLINKS = 40;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @Getter
    private final DirInode root;

    public FileSystem() {
        this.root = new DirInode();
        this.root.setLinkCount(1);
    }

    private Inode walk(DirInode start, Deque<String> queue, boolean followLast, int[] followed) {
        DirInode cur = start;
        Inode result = start;
        while (!queue.isEmpty()) {
            String name = queue.pollFirst();
            boolean isLast = queue.isEmpty();

            if ("..".equals(name)) {
                cur = cur.getParent() == null ? root : cur.getParent();
                result = cur;
                continue;
            }

            Inode next = cur.lookup(name).orElseThrow(() -> new FsException(FsErrorCode.ENOENT, name));
            if (next instanceof SymlinkInode sl && (!isLast || followLast)) {
                if (++followed[0] > MAX_SYMLINKS) {
                    throw new FsException(FsErrorCode.ELOOP, sl.getTarget());
                }
                ParsedPath p = ParsedPath.split(sl.getTarget());
                if (p.absolute()) {
                    cur = root;
                }
                List<String> t = p.parts();
                for (int i = t.size() - 1; i >= 0; i--) {
                    queue.addFirst(t.get(i));
                }
                continue;
            }

            if (isLast) {
                return result;
            }
            if (!(next instanceof DirInode d)) {
                throw new FsException(FsErrorCode.ENOTDIR, name);
            }
            cur = d;
            result = cur;
        }
        return result;
    }

    Inode resolve(DirInode base, String path, boolean followLast) {
        ParsedPath p = ParsedPath.split(path);
        DirInode start = p.absolute() ? root : base;
        return walk(start, new ArrayDeque<>(p.parts()), followLast, new int[]{0});
    }

    private Inode resolveOrNull(DirInode base, String path, boolean followLast) {
        try {
            return resolve(base, path, followLast);
        } catch (FsException e) {
            return null;
        }
    }

    private Slot resolveSlot(DirInode base, String path) {
        ParsedPath p = ParsedPath.split(path);
        List<String> parts = p.parts();
        if (parts.isEmpty()) {
            throw new FsException(FsErrorCode.EBUSY, path);
        }
        String name = parts.getLast();
        if ("..".equals(name)) {
            throw new FsException(FsErrorCode.EINVAL, name);
        }

        DirInode start = p.absolute() ? root : base;
        Deque<String> q = new ArrayDeque<>(parts.subList(0, parts.size() - 1));
        Inode dir = walk(start, q, true, new int[]{0});
        if (!(dir instanceof DirInode d)) {
            throw new FsException(FsErrorCode.ENOTDIR, path);
        }
        return new Slot(d, name);
    }

    public List<String> ls(DirInode base, String path) {
        readLock.lock();
        try {
            Inode n = resolve(base, path, true);
            if (n instanceof DirInode d) {
                return new ArrayList<>(d.names());
            }
            return List.of(baseNameOf(path));
        } finally {
            readLock.unlock();
        }
    }

    public boolean exists(DirInode base, String path) {
        readLock.lock();
        try {
            return resolveOrNull(base, path, true) != null;
        } finally {
            readLock.unlock();
        }
    }

    public Stat stat(DirInode base, String path) {
        readLock.lock();
        try {
            Inode n = resolve(base, path, true);
            return snapshot(n);
        } finally {
            readLock.unlock();
        }
    }

    private Stat snapshot(Inode node) {
        Kind kind = Kind.of(node);
        String target = null;
        if (kind == Kind.SYMLINK) {
            target = ((SymlinkInode) node).getTarget();
        }
        return new Stat(node.id, kind, node.size(), node.getLinkCount(), node.createdAt, node.modifiedAt, target);
    }

    public long du(DirInode base, String path) {
        long total = 0;
        readLock.lock();
        try {
            Inode n = resolve(base, path, true);
            Deque<Inode> stack = new ArrayDeque<>();
            stack.push(n);
            while (!stack.isEmpty()) {
                Inode cur = stack.pop();
                if (cur instanceof DirInode d) {
                    for (String name : d.names()) {
                        d.lookup(name).ifPresent(stack::push);
                    }
                } else {
                    total += cur.size();
                }
            }
            return total;
        } finally {
            readLock.unlock();
        }
    }

    public void mv(DirInode base, String srcPath, String dstPath) {
        writeLock.lock();
        try {
            mv0(base, srcPath, dstPath);
        } finally {
            writeLock.unlock();
        }
    }

    private void mv0(DirInode base, String srcPath, String dstPath) {
        Slot src = resolveSlot(base, srcPath);
        Inode node = src.dir().lookup(src.name()).orElseThrow(() -> new FsException(FsErrorCode.ENOENT, srcPath));

        DirInode dstDir;
        String dstName;
        Inode existing = resolveOrNull(base, dstPath, true);
        if (existing instanceof DirInode d) {
            dstDir = d;
            dstName = src.name();
        } else {
            Slot s = resolveSlot(base, dstPath);
            dstDir = s.dir();
            dstName = s.name();
        }

        if (src.dir() == dstDir && src.name().equals(dstName)) {
            return;
        }
        if (node instanceof DirInode sd && isSelfOrAncestor(sd, dstDir)) {
            throw new FsException(FsErrorCode.EINVAL, srcPath);
        }
        if (dstDir.lookup(dstName).isPresent()) {
            throw new FsException(FsErrorCode.EEXIST, srcPath);
        }

        src.dir().detach(src.name());
        dstDir.attach(dstName, node);
    }

    private boolean isSelfOrAncestor(DirInode src, DirInode dst) {
        for (DirInode cur = dst; cur != null; cur = cur.getParent()) {
            if (cur == src) {
                return true;
            }
        }
        return false;
    }

    public String pathOf(DirInode dir) {
        readLock.lock();
        try {
            if (dir.getParent() == null) {
                return SEP;
            }
            Deque<String> stack = new ArrayDeque<>();
            for (DirInode cur = dir; cur != null; cur = cur.getParent()) {
                stack.addFirst(cur.getParent().nameOfChild(cur));
            }
            return SEP + String.join(SEP, stack);
        } finally {
            readLock.unlock();
        }
    }

    public DirInode mkdir(DirInode base, String path) {
        writeLock.lock();
        try {
            Slot s = resolveSlot(base, path);
            DirInode d = new DirInode();
            s.dir().attach(s.name(), d);
            return d;
        } finally {
            writeLock.unlock();
        }
    }

    public DirInode mkdirs(DirInode base, String path) {
        writeLock.lock();
        try {
            ParsedPath p = ParsedPath.split(path);
            DirInode cur = p.absolute() ? root : base;
            for (String name : p.parts()) {
                if ("..".equals(name)) {
                    cur = cur.getParent() == null ? root : cur.getParent();
                    continue;
                }
                Inode next = cur.lookup(name).orElse(null);
                if (next == null) {
                    DirInode d = new DirInode();
                    cur.attach(name, d);
                    cur = d;
                } else if (next instanceof DirInode d) {
                    cur = d;
                } else {
                    throw new FsException(FsErrorCode.ENOTDIR, name);
                }
            }
            return cur;
        } finally {
            writeLock.unlock();
        }
    }

    public FileInode touch(DirInode base, String path) {
        writeLock.lock();
        try {
            Slot s = resolveSlot(base, path);
            FileInode f = new FileInode();
            s.dir().attach(s.name(), f);
            return f;
        } finally {
            writeLock.unlock();
        }
    }

    public void link(DirInode base, String path, String newPath) {
        writeLock.lock();
        try {
            Inode target = resolve(base, path, true);
            if (target instanceof DirInode) {
                throw new FsException(FsErrorCode.EPERM, path);
            }
            Slot s = resolveSlot(base, newPath);
            s.dir().attach(s.name(), target);
        } finally {
            writeLock.unlock();
        }
    }

    public void unlink(DirInode base, String path) {
        writeLock.lock();
        try {
            Slot s = resolveSlot(base, path);
            Inode n = s.dir().lookup(s.name()).orElseThrow(() -> new FsException(FsErrorCode.ENOENT, s.name()));
            if (n instanceof DirInode) {
                throw new FsException(FsErrorCode.EISDIR, path);
            }
            s.dir().detach(s.name());
        } finally {
            writeLock.lock();
        }
    }

    public void symlink(DirInode base, String target, String linkPath) {
        writeLock.lock();
        try {
            Slot s = resolveSlot(base, linkPath);
            s.dir().attach(s.name(), new SymlinkInode(target));
        } finally {
            writeLock.unlock();
        }
    }

    public void rm(DirInode base, String path) {
        writeLock.lock();
        try {
            rm0(base, path, false);
        } finally {
            writeLock.unlock();
        }
    }

    public void rmForce(DirInode base, String path) {
        writeLock.lock();
        try {
            rm0(base, path, true);
        } catch (FsException e) {
            if (!e.getCode().equals(FsErrorCode.ENOENT)) {
                throw e;
            }
        } finally {
            writeLock.unlock();
        }
    }

    public void rmRecursive(DirInode base, String path) {
        writeLock.lock();
        try {
            rm0(base, path, true);
        } finally {
            writeLock.unlock();
        }
    }

    private void rm0(DirInode base, String path, boolean recursive) {
        Slot s = resolveSlot(base, path);
        Inode victim = s.dir().lookup(s.name()).orElseThrow(() -> new FsException(FsErrorCode.ENOENT, s.name()));

        if (victim == root) {
            throw new FsException(FsErrorCode.ENOENT, path);
        }
        if (victim instanceof DirInode d) {
            if (!recursive) {
                throw new FsException(FsErrorCode.EISDIR, path);
            }
            if (isSelfOrAncestor(d, base)) {
                throw new FsException(FsErrorCode.EBUSY, path);
            }
        }
        s.dir().detach(s.name());

        if (victim instanceof DirInode d) {
            purge(d);
        }
    }

    private void purge(DirInode base) {
        Deque<DirInode> stack = new ArrayDeque<>();
        stack.push(base);
        while (!stack.isEmpty()) {
            DirInode d = stack.pop();
            for (String name : d.names()) {
                Inode child = d.detach(name);
                if (child instanceof DirInode cd) {
                    stack.push(cd);
                }
            }
        }
    }

    public void rmdir(DirInode base, String path) {
        writeLock.lock();
        try {
            Slot s = resolveSlot(base, path);
            Inode n = s.dir().lookup(s.name()).orElseThrow(() -> new FsException(FsErrorCode.ENOENT, s.name()));
            if (!(n instanceof DirInode d)) {
                throw new FsException(FsErrorCode.ENOTDIR, path);
            }
            if (!d.isEmpty()) {
                throw new FsException(FsErrorCode.ENOTEMPTY, path);
            }
            s.dir().detach(s.name());
        } finally {
            writeLock.unlock();
        }
    }

    public String readFile(DirInode base, String path) {
        readLock.lock();
        try {
            FileInode f = fileAt(base, path, false);
            return f.read();
        } finally {
            readLock.unlock();
        }
    }

    public void writeToFile(DirInode base, String path, String content) {
        writeLock.lock();
        try {
            fileAt(base, path, true).writeToFile(content);
        } finally {
            writeLock.unlock();
        }
    }

    public void appendToFile(DirInode base, String path, String content) {
        writeLock.lock();
        try {
            fileAt(base, path, true).appendToFile(content);
        } finally {
            writeLock.unlock();
        }
    }

    private FileInode fileAt(DirInode base, String path, boolean createIfAbsent) {
        Inode n = resolveOrNull(base, path, true);
        if (n == null) {
            if (!createIfAbsent) {
                throw new FsException(FsErrorCode.ENOTDIR, path);
            }
            return touch(base, path);
        }
        if (!(n instanceof FileInode f)) {
            throw new FsException(FsErrorCode.EISDIR, path);
        }
        return f;
    }

    private String baseNameOf(String path) {
        List<String> parts = ParsedPath.split(path).parts();
        if (parts.isEmpty()) {
            return SEP;
        }
        return parts.getLast();
    }

}
