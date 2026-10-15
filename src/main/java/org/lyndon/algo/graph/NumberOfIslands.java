package org.lyndon.algo.graph;

public class NumberOfIslands {

    private static int numIslands(char[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    expand(grid, i, j, m, n);
                    ans++;
                }
            }
        }
        return ans;
    }

    private static void expand(char[][] grid, int i, int j, int m, int n) {
        if (grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        if (i > 0) {
            expand(grid, i - 1, j, m, n);
        }
        if (j > 0) {
            expand(grid, i, j - 1, m, n);
        }
        if (i < m - 1) {
            expand(grid, i + 1, j, m, n);
        }
        if (j < n - 1) {
            expand(grid, i, j + 1, m, n);
        }
    }

}
