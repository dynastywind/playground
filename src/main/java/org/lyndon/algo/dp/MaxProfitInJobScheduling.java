package org.lyndon.algo.dp;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * LC1235
 */
public class MaxProfitInJobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1]));
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int start = jobs[i - 1][0];
            int j = search(jobs, i - 1, start);
            dp[i] = Math.max(dp[i - 1], dp[j] + jobs[i - 1][2]);
        }
        return dp[n];
    }

    private int search(int[][] jobs, int hi, int target) {
        int lo = 0;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (jobs[mid][1] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public int jobSchedulingTreeMap(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1]));

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int select = dp.floorEntry(job[0]).getValue() + job[2];
            if (select > dp.lastEntry().getValue()) {
                dp.put(job[1], select);
            }
        }
        return dp.lastEntry().getValue();
    }

}
