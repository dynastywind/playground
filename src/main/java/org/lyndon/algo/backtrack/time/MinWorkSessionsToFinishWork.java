package org.lyndon.algo.backtrack.time;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LC1986
 * There are n tasks assigned to you. The task times are represented as an integer array tasks of length n, where the ith task takes tasks[i] hours to finish. A work session is when you work for at most sessionTime consecutive hours and then take a break.
 * <p>
 * You should finish the given tasks in a way that satisfies the following conditions:
 * <p>
 * If you start a task in a work session, you must complete it in the same work session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * Given tasks and sessionTime, return the minimum number of work sessions needed to finish all the tasks following the conditions above.
 * <p>
 * The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].
 * <p>
 * Example 1:
 * <p>
 * Input: tasks = [1,2,3], sessionTime = 3
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
 * - Second work session: finish the third task in 3 hours.
 * Example 2:
 * <p>
 * Input: tasks = [3,1,3,1,1], sessionTime = 8
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
 * - Second work session: finish the last task in 1 hour.
 * Example 3:
 * <p>
 * Input: tasks = [1,2,3,4,5], sessionTime = 15
 * Output: 1
 * Explanation: You can finish all the tasks in one work session.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == tasks.length
 * 1 <= n <= 14
 * 1 <= tasks[i] <= 10
 * max(tasks[i]) <= sessionTime <= 15
 */
public class MinWorkSessionsToFinishWork {

    public int minSessions(int[] tasks, int sessionTime) {
        /*
            OPT * sessionTime >= sum of tasks
            OPT >= Math.ceil(sum of tasks / sessionTime)
         */
        int sum = 0;
        for (int task : tasks) {
            sum += task;
        }
        int low = (sum + sessionTime - 1) / sessionTime;
        int high = tasks.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (backtrack(tasks, sessionTime, 0, new int[mid])) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean backtrack(int[] tasks, int sessionTime, int index, int[] segments) {
        if (index == tasks.length) {
            return true;
        }
        Set<Integer> tried = new HashSet<>();
        for (int i = 0; i < segments.length; i++) {
            if (segments[i] + tasks[index] > sessionTime) {
                continue;
            }
            if (!tried.add(segments[i])) {
                continue;
            }
            segments[i] += tasks[index];
            if (backtrack(tasks, sessionTime, index + 1, segments)) {
                return true;
            }
            segments[i] -= tasks[index];
        }
        return false;
    }

    public int minSessionsDP(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int full = 1 << n;
        int[] sum = new int[full];
        for (int mask = 1; mask < full; mask++) {
            int low = Integer.numberOfTrailingZeros(mask);
            sum[mask] = tasks[low] + sum[mask & (mask - 1)];
        }

        int[] dp = new int[full];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int mask = 1; mask < full; mask++) {
            for (int s = mask; s > 0; s = mask & (s - 1)) {
                if (sum[s] <= sessionTime) {
                    dp[mask] = Math.min(dp[mask], dp[mask ^ s] + 1);
                }
            }
        }
        return dp[full - 1];
    }

}
