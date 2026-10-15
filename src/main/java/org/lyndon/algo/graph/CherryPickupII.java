package org.lyndon.algo.graph;

/**
 * LC1463
 */
public class CherryPickupII {

    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 从第 i 行开始，A 在列 j1、B 在列 j2，一直走到最后一行，最多能摘到的樱桃
        int[][][] dp = new int[m][n][n];

        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++) {
                dp[m - 1][j1][j2] = grid[m - 1][j1] + (j1 == j2 ? 0 : grid[m - 1][j2]);
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < n; j1++) {
                for (int j2 = 0; j2 < n; j2++) {
                    int best = 0;
                    for (int d1 = -1; d1 <= 1; d1++) {
                        int nj1 = j1 + d1;
                        if (nj1 < 0 || nj1 >= n) {
                            continue;
                        }
                        for (int d2 = -1; d2 <= 1; d2++) {
                            int nj2 = j2 + d2;
                            if (nj2 < 0 || nj2 >= n) {
                                continue;
                            }
                            best = Math.max(best, dp[i + 1][nj1][nj2]);
                        }
                    }
                    int gain = grid[i][j1] + (j1 == j2 ? 0 : grid[i][j2]);
                    dp[i][j1][j2] = gain + best;
                }
            }
        }

        return dp[0][0][n - 1];
    }

}
