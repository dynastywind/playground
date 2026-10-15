package org.lyndon.design.fs;

public final class FileInode extends Inode {

    private final StringBuilder sb = new StringBuilder();

    public void writeToFile(final String fileName) {
        sb.setLength(0);
        sb.append(fileName);
        touch();
    }

    public void appendToFile(String content) {
        sb.append(content);
        touch();
    }

    public String read() {
        return sb.toString();
    }

    public long size() {
        return sb.length();
    }

}
