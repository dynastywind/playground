package org.lyndon.algo.dp;

/**
 * LC940
 */
public class DistinctSubsequencesII {

    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        int[] seen = new int[26];
        for (int i = 1; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2) % MOD;
            int c = s.charAt(i - 1) - 'a';
            if (seen[c] != 0) {
                dp[i] = Math.floorMod(dp[i] - dp[seen[c] - 1], MOD);
            }
            seen[c] = i;
        }
        return Math.floorMod(dp[n] - 1, MOD);
    }

}
