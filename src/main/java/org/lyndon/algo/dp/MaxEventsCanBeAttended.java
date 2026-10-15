package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC1751
 */
public class MaxEventsCanBeAttended {

    public int maxValue(int[][] events, int k) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        // first meeting whose start_time > end_i
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n;
            int end = events[i][1];
            while (lo < hi) {
                int mid = (lo + hi) >> 1;
                if (events[mid][0] <= end) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            next[i] = lo;
        }

        // suffix dp. max profit when selecting meetings from ith meeting with j meetings left to select
        int[][] dp = new int[n + 1][k + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], events[i][2] + dp[next[i]][j - 1]);
            }
        }
        return dp[0][k];
    }

    public int maxValuePrefixDP(int[][] events, int k) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[1], b[1]));

        // select at most j meetings from previous i meetings
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            int[] event = events[i - 1];

            // meetings whose end_time < event.start
            int lo = 0;
            int hi = i - 1;
            while (lo < hi) {
                int mid = (lo + hi) >> 1;
                if (events[mid][1] < event[0]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            for (int j = 1; j <= k; j++) {
                // dp[i-1][j]: don't select
                // event[2] + dp[lo][j-1]: select ith meeting
                dp[i][j] = Math.max(dp[i - 1][j], event[2] + dp[lo][j - 1]);
            }
        }
        return dp[n][k];
    }

}
