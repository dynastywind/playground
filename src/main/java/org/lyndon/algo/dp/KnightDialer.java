package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC935
 */
public class KnightDialer {

    public int knightDialer(int n) {
        final int[][] moves = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        final int MOD = 1_000_000_007;

        long[] dp = new long[10];
        Arrays.fill(dp, 1L);
        for (int i = 1; i < n; i++) {
            long[] next = new long[10];
            for (int d = 0; d < 10; d++) {
                for (int prev : moves[d]) {
                    next[d] = (next[d] + dp[prev]) % MOD;
                }
            }
            dp = next;
        }

        long ans = 0;
        for (long v : dp) {
            ans = (ans + v) % MOD;
        }
        return (int) ans;
    }

}
