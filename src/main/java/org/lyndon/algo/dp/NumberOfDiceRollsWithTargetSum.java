package org.lyndon.algo.dp;

/**
 * LC1155
 */
public class NumberOfDiceRollsWithTargetSum {

    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                for (int m = 1; m <= k; m++) {
                    if (j - m < 0) {
                        break;
                    }
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - m]) % 1_000_000_007;
                }
            }
        }
        return dp[n][target];
    }

}
