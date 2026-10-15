package org.lyndon.algo.graph;

import java.util.Arrays;

/**
 * LC741
 * You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.
 * <p>
 * 0 means the cell is empty, so you can pass through,
 * 1 means the cell contains a cherry that you can pick up and pass through, or
 * -1 means the cell contains a thorn that blocks your way.
 * Return the maximum number of cherries you can collect by following the rules below:
 * <p>
 * Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
 * After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
 * When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
 * If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
 */
public class CherryPickup {

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int K = 2 * n - 1;
        // dp[k][i][j] = 走了 k 步，A 在 (i, k-i)、B 在 (j, k-j) 时的最大樱桃数
        int[][][] dp = new int[K][n][n];
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, Integer.MIN_VALUE / 2);
            }
        }
        dp[0][0][0] = grid[0][0];

        for (int k = 1; k < K; k++) {
            // 0 <= k - i <= n - 1 and 0 <= i <= n - 1
            // k - n + 1 <= i <= k and 0 <= i <= n - 1
            int lo = Math.max(0, k - n + 1);
            int hi = Math.min(k, n - 1);
            for (int i = lo; i <= hi; i++) {
                if (grid[i][k - i] == -1) {
                    continue;
                }
                for (int j = lo; j <= hi; j++) {
                    if (grid[j][k - j] == -1) {
                        continue;
                    }
                    // A from left, B from left
                    int best = dp[k - 1][i][j];
                    if (i > 0) {
                        // A from top, B from left
                        best = Math.max(best, dp[k - 1][i - 1][j]);
                    }
                    if (j > 0) {
                        // A from left, B from top
                        best = Math.max(best, dp[k - 1][i][j - 1]);
                    }
                    if (i > 0 && j > 0) {
                        // A from top, B from top
                        best = Math.max(best, dp[k - 1][i - 1][j - 1]);
                    }
                    if (best < 0) {
                        continue;
                    }
                    int val = grid[i][k - i];
                    if (i != j) {
                        val += grid[j][k - j];
                    }
                    dp[k][i][j] = best + val;
                }
            }
        }
        return Math.max(0, dp[K - 1][n - 1][n - 1]);
    }

}
