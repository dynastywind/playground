package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC1066
 */
public class CampusBikesII {

    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int MASK = 1 << m;
        int[] dp = new int[MASK];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int ans = Integer.MAX_VALUE;
        for (int mask = 0; mask < MASK; mask++) {
            if (dp[mask] == Integer.MAX_VALUE) {
                continue;
            }
            int i = Integer.bitCount(mask);
            if (i == n) {
                ans = Math.min(ans, dp[mask]);
                continue;
            }
            for (int j = 0; j < m; j++) {
                if ((mask & (1 << j)) != 0) {
                    continue;
                }
                int next = mask | (1 << j);
                dp[next] = Math.min(dp[next], dp[mask] + dist(workers[i], bikes[j]));
            }
        }
        return ans;
    }

    private int dist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

}
