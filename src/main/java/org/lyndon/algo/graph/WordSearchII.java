package org.lyndon.algo.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * LC212
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 */
public class WordSearchII {

    private static class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];

        public void insert(String word) {
            TrieNode node = this;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            root.insert(word);
        }

        List<String> found = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                search(board, i, j, root, found);
            }
        }
        return found;
    }

    private void search(char[][] board, int r, int c, TrieNode node, List<String> found) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
            return;
        }
        char ch = board[r][c];
        if (ch == '#') {
            return;
        }
        TrieNode child = node.children[ch - 'a'];
        if (child == null) {
            return;
        }
        if (child.word != null) {
            found.add(child.word);
            child.word = null;
        }
        board[r][c] = '#';
        search(board, r - 1, c, child, found);
        search(board, r, c - 1, child, found);
        search(board, r + 1, c, child, found);
        search(board, r, c + 1, child, found);
        board[r][c] = ch;
    }

}
