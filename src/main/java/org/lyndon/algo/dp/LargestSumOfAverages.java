package org.lyndon.algo.dp;

/**
 * LC813
 */
public class LargestSumOfAverages {

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] pre = new double[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }

        double[][] dp = new double[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = pre[i] / i;
        }

        for (int j = 2; j <= k; j++) {
            for (int i = j; i <= n; i++) {
                for (int p = j - 1; p < i; p++) {
                    dp[i][j] = Math.max(dp[i][j], dp[p][j - 1] + (pre[i] - pre[p]) / (i - p));
                }
            }
        }
        return dp[n][k];
    }

}
