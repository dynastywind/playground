package org.lyndon.algo.dp;

/**
 * LC688
 */
public class KnightProbInBoard {

    public double knightProbability(int n, int k, int row, int column) {
        final int[][] dirs = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        // dp[step][r][c] 表示"从 (r,c) 出发再走 step 步后仍在棋盘上"的概率
        double[][][] dp = new double[k + 1][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[0][i][j] = 1;
            }
        }

        for (int move = 1; move <= k; move++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double sum = 0;
                    for (int[] dir : dirs) {
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                            continue;
                        }
                        sum += dp[move - 1][nr][nc];
                    }
                    dp[move][i][j] = sum / 8.0;
                }
            }
        }
        return dp[k][row][column];
    }

}
