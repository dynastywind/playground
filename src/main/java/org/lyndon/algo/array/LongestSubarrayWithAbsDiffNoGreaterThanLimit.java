package org.lyndon.algo.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC1438
 */
public class LongestSubarrayWithAbsDiffNoGreaterThanLimit {

    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> min = new ArrayDeque<>();
        Deque<Integer> max = new ArrayDeque<>();
        int left = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!min.isEmpty() && nums[min.peekLast()] > nums[i]) {
                min.pollLast();
            }
            min.addLast(i);
            while (!max.isEmpty() && nums[max.peekLast()] < nums[i]) {
                max.pollLast();
            }
            max.addLast(i);

            while (nums[max.peekFirst()] - nums[min.peekFirst()] > limit) {
                if (max.peekFirst() == left) {
                    max.pollFirst();
                }
                if (min.peekFirst() == left) {
                    min.pollFirst();
                }
                left++;
            }
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

}
