package org.lyndon.design.fs;

public enum Kind {
    FILE, DIR, SYMLINK;

    public static Kind of(Inode node) {
        if (node instanceof DirInode) {
            return DIR;
        } else if (node instanceof FileInode) {
            return FILE;
        } else if (node instanceof SymlinkInode) {
            return SYMLINK;
        }
        throw new IllegalArgumentException("Unknown Kind: " + node);
    }

}
