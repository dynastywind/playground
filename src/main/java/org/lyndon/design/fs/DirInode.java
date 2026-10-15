package org.lyndon.design.fs;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

public final class DirInode extends Inode {

    @Getter
    private final Map<String, Inode> children;

    @Getter
    private DirInode parent;

    public DirInode() {
        this.children = new HashMap<>();
    }

    public Optional<Inode> lookup(String name) {
        return Optional.ofNullable(children.get(name));
    }

    public void attach(String name, Inode child) {
        if (children.putIfAbsent(name, child) != null) {
            throw new FsException(FsErrorCode.EEXIST, name);
        }
        if (child instanceof DirInode d) {
            d.parent = this;
        }
        child.setLinkCount(child.getLinkCount() + 1);
        touch();
    }

    public Inode detach(String name) {
        Inode child = children.remove(name);
        if (child == null) {
            throw new FsException(FsErrorCode.ENOENT, name);
        }
        if (child instanceof DirInode d) {
            d.parent = null;
        }
        child.setLinkCount(child.getLinkCount() - 1);
        touch();
        return child;
    }

    public TreeSet<String> names() {
        return new TreeSet<>(children.keySet());
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }

    public String nameOfChild(Inode child) {
        for (var e : children.entrySet()) {
            if (e.getValue() == child) {
                return e.getKey();
            }
        }
        throw new FsException(FsErrorCode.ENOENT, "" + child.id);
    }

    @Override
    public long size() {
        return children.size();
    }

}
