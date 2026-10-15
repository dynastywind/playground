package org.lyndon.algo.dp;

/**
 * LC3524
 */
public class FindXValueOfArray {

    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] res = new long[k];
        long[][] dp = new long[n][k];

        for (int i = 0; i < n; i++) {
            int x = nums[i] % k;
            dp[i][x] = 1;
            if (i > 0) {
                for (int r = 0; r < k; r++) {
                    // (prefix * nums[i]) % k = ((prefix % k) * (nums[i] % k)) % k = r * x % k
                    dp[i][r * x % k] += dp[i - 1][r];
                }
            }
            for (int r = 0; r < k; r++) {
                res[r] += dp[i][r];
            }
        }
        return res;
    }

}
