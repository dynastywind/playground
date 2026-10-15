package org.lyndon.algo.graph;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j, m, n, new HashSet<>())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int i, int j, int m, int n, Set<Integer> visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(index)) {
            return false;
        }
        int hash = genHash(i, j, n);
        if (visited.contains(hash)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        visited.add(hash);
        boolean found = dfs(board, word, index + 1, i - 1, j, m, n, visited) ||
                dfs(board, word, index + 1, i, j - 1, m, n, visited) ||
                dfs(board, word, index + 1, i + 1, j, m, n, visited) ||
                dfs(board, word, index + 1, i, j + 1, m, n, visited);
        visited.remove(hash);
        return found;
    }

    private int genHash(int i, int j, int n) {
        return i * n + j;
    }

    public static void main(String[] args) {
        var solution = new WordSearch();
//        System.out.println(solution.exist(new char[][]{new char[]{'a'}}, "a"));
        System.out.println(solution.exist(new char[][]{new char[]{'A', 'B', 'C', 'E'}, new char[]{'S', 'F', 'C', 'S'}, new char[]{'A', 'D', 'E', 'E'}}, "ABCB"));
    }

}
