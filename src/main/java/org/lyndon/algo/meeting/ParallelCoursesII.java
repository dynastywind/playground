package org.lyndon.algo.meeting;

import java.util.Arrays;

/**
 * LC1494
 */
public class ParallelCoursesII {

    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] pre = new int[n];
        for (int[] relation : relations) {
            pre[relation[1] - 1] |= 1 << (relation[0] - 1);
        }

        final int full = 1 << n;
        int[] dp = new int[full];
        Arrays.fill(dp, n);
        dp[0] = 0;

        for (int mask = 0; mask < full; mask++) {
            int can = 0;
            for (int i = 0; i < n; i++) {
                if ((mask >> i & 1) == 0 && (pre[i] & mask) == pre[i]) {
                    can |= 1 << i;
                }
            }

            if (Integer.bitCount(can) <= k) {
                int next = mask | can;
                dp[next] = Math.min(dp[next], dp[mask] + 1);
                continue;
            }

            for (int sub = can; sub > 0; sub = (sub - 1) & can) {
                if (Integer.bitCount(sub) != k) {
                    continue;
                }
                int next = mask | sub;
                dp[next] = Math.min(dp[next], dp[mask] + 1);
            }
        }
        return dp[full - 1];
    }

}
