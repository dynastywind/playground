package org.lyndon.algo.dp;

/**
 * LC1277
 */
public class CountSquareSubmatricesWithAllOnes {

    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
            ans += dp[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = matrix[0][j];
            ans += dp[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != 0) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    ans++;
                }
            }
        }
        return ans;
    }

}
