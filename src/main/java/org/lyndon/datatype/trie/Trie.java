package org.lyndon.datatype.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private final Map<Character, Trie> children;

    private boolean isEnd;

    public Trie() {
        this.children = new HashMap<>();
        this.isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            node.children.putIfAbsent(word.charAt(i), new Trie());
            node = node.children.get(word.charAt(i));
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie found = searchPrefix(word);
        return found != null && found.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            node = node.children.get(word.charAt(i));
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    public void delete(String word) {
        delete(this, word, 0);
    }

    private boolean delete(Trie node, String word, int depth) {
        if (depth == word.length()) {
            if (!node.isEnd) {
                return false;
            }
            node.isEnd = false;
            return node.children.isEmpty();
        }
        char c = word.charAt(depth);
        Trie child = node.children.get(c);
        if (child == null) {
            return false;
        }
        if (delete(child, word, depth + 1)) {
            node.children.remove(c);
        }
        return !node.isEnd && node.children.isEmpty();
    }

}
