package org.lyndon.algo.dp;

/**
 * LC983
 */
public class MinCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        boolean[] travel = new boolean[lastDay + 1];
        for (int day : days) {
            travel[day] = true;
        }
        for (int i = 1; i <= lastDay; i++) {
            if (!travel[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            dp[i] = Math.min(Math.min(dp[Math.max(0, i - 7)] + costs[1], dp[Math.max(0, i - 30)] + costs[2]), dp[i - 1] + costs[0]);
        }
        return dp[lastDay];
    }

}
