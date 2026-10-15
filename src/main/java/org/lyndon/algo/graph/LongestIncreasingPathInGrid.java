package org.lyndon.algo.graph;

/**
 * LC329
 */
public class LongestIncreasingPathInGrid {

    public int longestIncreasingPath(int[][] matrix) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(dfs(matrix, i, j, m, n, memo, dirs), ans);
            }
        }
        return ans;
    }

    private int dfs(int[][] matrix, int r, int c, int m, int n, int[][] memo, int[][] dirs) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }
        int best = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr <= 0 || nr >= m || nc <= 0 || nc >= n || matrix[nr][nc] <= matrix[r][c]) {
                continue;
            }
            best = Math.max(1 + dfs(matrix, nr, nc, m, n, memo, dirs), best);
        }
        memo[r][c] = best;
        return best;
    }

}
