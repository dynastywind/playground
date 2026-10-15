package org.lyndon.algo.graph;

/**
 * LC980
 * You are given an m x n integer array grid where grid[i][j] could be:
 * <p>
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once
 */
public class UniquePathIII {

    private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int ans;

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int total = 0;
        int sr = 0, sc = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != -1) {
                    total++;
                }
                if (grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
            }
        }
        dfs(grid, sr, sc, m, n, 1, total);
        return ans;
    }

    private void dfs(int[][] grid, int r, int c, int m, int n, int cnt, int total) {
        if (grid[r][c] == 2) {
            if (cnt == total) {
                ans++;
            }
            return;
        }
        int o = grid[r][c];
        grid[r][c] = -1;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] == -1) {
                continue;
            }
            dfs(grid, nr, nc, m, n, cnt + 1, total);
        }
        grid[r][c] = o;
    }

}
