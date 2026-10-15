package org.lyndon.design;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * LC588
 * Design a data structure that simulates an in-memory file system.
 * <p>
 * Implement the FileSystem class:
 * <p>
 * FileSystem() Initializes the object of the system.
 * List<String> ls(String path)
 * If path is a file path, returns a list that only contains this file's name.
 * If path is a directory path, returns the list of file and directory names in this directory.
 * The answer should in lexicographic order.
 * void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
 * void addContentToFile(String filePath, String content)
 * If filePath does not exist, creates that file containing given content.
 * If filePath already exists, appends the given content to original content.
 * String readContentFromFile(String filePath) Returns the content in the file at filePath.
 */
public class FileSystem {

    private static class TrieNode {
        final TreeMap<String, TrieNode> children;
        final boolean isFile;
        final StringBuilder content;

        public TrieNode(boolean isFile) {
            this.isFile = isFile;
            this.children = new TreeMap<>();
            this.content = isFile ? new StringBuilder() : null;
        }
    }

    private final TrieNode root;

    public FileSystem() {
        this.root = new TrieNode(false);
    }

    public List<String> ls(String path) {
        String[] paths = split(path);
        TrieNode cur = root;
        for (String p : paths) {
            cur = cur.children.get(p);
        }

        List<String> res = new ArrayList<>();
        if (cur.isFile) {
            res.add(paths[paths.length - 1]);
        } else {
            res.addAll(cur.children.keySet());
        }
        return res;
    }

    public void mkdir(String path) {
        String[] paths = split(path);
        mkdir(paths);
    }

    private TrieNode mkdir(String[] paths) {
        TrieNode cur = root;
        for (String p : paths) {
            cur = cur.children.computeIfAbsent(p, k -> new TrieNode(false));
        }
        return cur;
    }

    public void addContentToFile(String filePath, String content) {
        int i = filePath.lastIndexOf("/");
        TrieNode cur = i == 0 ? root : mkdir(split(filePath.substring(0, i)));
        String fileName = filePath.substring(i + 1);
        TrieNode file = cur.children.computeIfAbsent(fileName, k -> new TrieNode(true));
        file.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        String[] paths = split(filePath);
        TrieNode cur = root;
        for (String p : paths) {
            cur = cur.children.get(p);
        }
        return cur.content.toString();
    }

    private String[] split(String path) {
        if ("/".equals(path)) {
            return new String[]{};
        }
        return path.substring(1).split("/");
    }

}
