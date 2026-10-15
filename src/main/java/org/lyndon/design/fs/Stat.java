package org.lyndon.design.fs;

public record Stat(long ino, Kind kind, long size, int linkCount, long createdAt, long modifiedAt, String linkTarget) {

    public boolean isDir() {
        return kind == Kind.DIR;
    }

    public boolean isFile() {
        return kind == Kind.FILE;
    }

    public boolean isSymlink() {
        return kind == Kind.SYMLINK;
    }

}
