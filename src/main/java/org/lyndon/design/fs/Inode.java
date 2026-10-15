package org.lyndon.design.fs;

import lombok.Getter;
import lombok.Setter;

public sealed abstract class Inode permits FileInode, DirInode, SymlinkInode {

    private static long SEQ = 0;

    final long id;

    final int permission;

    final long createdAt;

    @Getter
    @Setter
    private int linkCount;

    long modifiedAt;

    public Inode() {
        this.id = SEQ++;
        this.permission = 7777;
        this.createdAt = System.currentTimeMillis();
    }

    public void touch() {
        modifiedAt = System.currentTimeMillis();
    }

    public abstract long size();

}
