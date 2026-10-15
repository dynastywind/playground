package org.lyndon.algo.graph;

import org.lyndon.utils.PrintUtils;

/**
 * LC130
 */
public class SurroundedRegions {

    private int[] parents;

    private int find(int x) {
        int root = x;
        while (root != parents[root]) {
            root = parents[root];
        }
        while (x != root) {
            int p = parents[x];
            parents[x] = root;
            x = p;
        }
        return root;
    }

    private void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return;
        }
        if (ra < rb) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }
        parents[rb] = ra;
    }

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int vId = m * n;
        parents = new int[m * n + 1];
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < m * n + 1; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                union(key(i, 0, n), vId);
            }
            if (board[i][n - 1] == 'O') {
                union(key(i, n - 1, n), vId);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                union(key(0, j, n), vId);
            }
            if (board[m - 1][j] == 'O') {
                union(key(m - 1, j, n), vId);
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    int key = key(i, j, n);
                    for (int[] dir : dirs) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if (board[nr][nc] == 'O') {
                            union(key(nr, nc, n), key);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < parents.length - 1; i++) {
            int[] coord = recover(i, n);
            int r = coord[0];
            int c = coord[1];
            if (board[r][c] == 'X') {
                continue;
            }
            if (find(parents[i]) != vId) {
                board[r][c] = 'X';
            }
        }
    }

    private int key(int r, int c, int n) {
        return r * n + c;
    }

    private int[] recover(int key, int n) {
        return new int[]{key / n, key % n};
    }

    public static void main(String[] args) {
        var solution = new SurroundedRegions();
        var borad = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solution.solve(borad);
        PrintUtils.printCharMatrix(borad);
    }

}
