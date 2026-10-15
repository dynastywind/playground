package org.lyndon.algo.backtrack.session;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LC1723
 * You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.
 * <p>
 * There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker. The working time of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.
 * <p>
 * Return the minimum possible maximum working time of any assignment.
 * <p>
 * Example 1:
 * <p>
 * Input: jobs = [3,2,3], k = 3
 * Output: 3
 * Explanation: By assigning each person one job, the maximum time is 3.
 * Example 2:
 * <p>
 * Input: jobs = [1,2,4,7,8], k = 2
 * Output: 11
 * Explanation: Assign the jobs the following way:
 * Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 * Worker 2: 4, 7 (working time = 4 + 7 = 11)
 * The maximum working time is 11.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 107
 */
public class MinTimeFinishAllJobs {

    private int ans = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        // descending: placing big jobs first raises worker loads early, so the >= ans cut fires at shallow depth
        for (int i = 0, j = jobs.length - 1; i < j; i++, j--) {
            int tmp = jobs[i];
            jobs[i] = jobs[j];
            jobs[j] = tmp;
        }
        backtrack(jobs, new int[k], 0);
        return ans;
    }

    private void backtrack(int[] jobs, int[] times, int job) {
        if (job == jobs.length) {
            int max = 0;
            for (int time : times) {
                max = Math.max(max, time);
            }
            ans = Math.min(ans, max);
            return;
        }
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < times.length; i++) {
            if (!seen.add(times[i])) {
                continue;
            }
            if (times[i] + jobs[job] >= ans) {
                continue;
            }
            times[i] += jobs[job];
            backtrack(jobs, times, job + 1);
            times[i] -= jobs[job];
        }
    }

    public int minimumTimeRequiredMask(int[] jobs, int k) {
        int n = jobs.length;
        int full = 1 << n;
        int[] sum = new int[full];
        for (int mask = 1; mask < full; mask++) {
            int i = Integer.numberOfTrailingZeros(mask);
            sum[mask] = sum[mask & (mask - 1)] + jobs[i];
        }
        int[] dp = sum.clone();
        for (int i = 2; i <= k; i++) {
            for (int s = full - 1; s > 0; s--) {
                for (int t = s; t > 0; t = (t - 1) & s) {
                    dp[s] = Math.min(dp[s], Math.max(dp[s ^ t], sum[t]));
                }
            }
        }
        return dp[full - 1];
    }

}
