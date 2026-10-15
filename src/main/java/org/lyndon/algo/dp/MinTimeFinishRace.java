package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC2188
 */
public class MinTimeFinishRace {

    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        final int MAX_LAPS = 18;
        final long INF = Long.MAX_VALUE / 4;

        long[] best = new long[MAX_LAPS + 1];
        Arrays.fill(best, INF);
        for (int[] tire : tires) {
            long f = tire[0];
            long r = tire[1];
            long cur = 1;
            long total = 0;
            for (int j = 1; j <= MAX_LAPS; j++) {
                total += f * cur;
                if (total > changeTime + f) {
                    break;
                }
                best[j] = Math.min(best[j], total);
                cur *= r;
            }
        }

        long[] dp = new long[numLaps + 1];
        Arrays.fill(dp, INF);
        for (int i = 1; i <= numLaps; i++) {
            if (i <= MAX_LAPS) {
                dp[i] = best[i];
            }
            for (int j = 1; j <= Math.min(i - 1, MAX_LAPS); j++) {
                if (dp[i - j] == INF || best[j] == INF) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - j] + changeTime + best[j]);
            }
        }
        return (int) dp[numLaps];
    }

}
