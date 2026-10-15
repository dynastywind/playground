package org.lyndon.design.fs;

public class Session {

    private final FileSystem fs;

    private DirInode cwd;

    public Session(FileSystem fs) {
        this.fs = fs;
    }

    public void chdir(String path) {
        Inode n = fs.resolve(cwd, path, true);
        if (!(n instanceof DirInode d)) {
            throw new FsException(FsErrorCode.ENOTDIR, path);
        }
        cwd = d;
    }

}
