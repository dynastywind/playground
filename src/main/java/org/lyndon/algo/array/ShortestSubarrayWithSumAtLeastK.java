package org.lyndon.algo.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC862
 */
public class ShortestSubarrayWithSumAtLeastK {

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> dq = new ArrayDeque<>();
        int ans = n + 1;
        for (int i = 0; i <= n; i++) {
            while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
                ans = Math.min(ans, i - dq.pollFirst());
            }
            while (!dq.isEmpty() && prefix[dq.peekLast()] >= prefix[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return ans > n ? -1 : ans;
    }

    public static void main(String[] args) {
        var solution = new ShortestSubarrayWithSumAtLeastK();
        System.out.println(solution.shortestSubarray(new int[]{1}, 1));
        System.out.println(solution.shortestSubarray(new int[]{1, 2}, 4));
        System.out.println(solution.shortestSubarray(new int[]{2, -1, 2}, 3));
        System.out.println(solution.shortestSubarray(new int[]{20, 32, 16, 41, -36}, 109));
    }

}
