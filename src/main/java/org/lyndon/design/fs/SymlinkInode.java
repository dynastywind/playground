package org.lyndon.design.fs;

import lombok.Getter;

public final class SymlinkInode extends Inode {

    @Getter
    private final String target;

    public SymlinkInode(String target) {
        this.target = target;
    }

    @Override
    public long size() {
        return target.length();
    }

}
