package org.lyndon.algo.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC2398
 * You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both of length n. The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run. You are also given an integer budget.
 * <p>
 * The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts), where max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of running costs among the k robots.
 * <p>
 * Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.
 * <p>
 * Example 1:
 * <p>
 * Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
 * Output: 3
 * Explanation:
 * It is possible to run all individual and consecutive pairs of robots within budget.
 * To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
 * It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
 * Example 2:
 * <p>
 * Input: chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
 * Output: 0
 * Explanation: No robot can be run that does not exceed the budget, so we return 0.
 */
public class MaxRobotsWithinBudget {

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + runningCosts[i];
        }

        int lo = 0;
        int hi = n;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (check(chargeTimes, prefix, budget, mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private boolean check(int[] chargeTimes, long[] prefix, long budget, int mid) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < chargeTimes.length; i++) {
            while (!queue.isEmpty() && chargeTimes[queue.peekLast()] <= chargeTimes[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            if (i >= mid + queue.peekFirst()) {
                queue.pollFirst();
            }

            if (i >= mid - 1) {
                long cost = chargeTimes[queue.peekFirst()] + (long) mid * (prefix[i + 1] - prefix[i - mid + 1]);
                if (cost <= budget) {
                    return true;
                }
            }
        }
        return false;
    }

}
