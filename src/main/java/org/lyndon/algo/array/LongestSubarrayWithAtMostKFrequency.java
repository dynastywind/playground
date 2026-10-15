package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC2958
 */
public class LongestSubarrayWithAtMostKFrequency {

    public int maxSubarrayLength(int[] nums, int k) {
        int left = 0;
        int ans = -1;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int c = cnt.merge(nums[i], 1, Integer::sum);
            if (c > k) {
                ans = Math.max(ans, i - left);
            }
            while (cnt.get(nums[i]) > k) {
                cnt.merge(nums[left++], -1, Integer::sum);
            }
        }
        return Math.max(ans, nums.length - left);
    }

    public static void main(String[] args) {
        var solution = new LongestSubarrayWithAtMostKFrequency();
        System.out.println(solution.maxSubarrayLength(new int[]{1, 2, 3, 1, 2, 3, 1, 2}, 2));
        System.out.println(solution.maxSubarrayLength(new int[]{1, 2, 1, 2, 1, 2, 1, 2}, 1));
        System.out.println(solution.maxSubarrayLength(new int[]{1, 1}, 1));
        System.out.println(solution.maxSubarrayLength(new int[]{5, 5, 5, 5, 5, 5, 5}, 4));
        System.out.println(solution.maxSubarrayLength(new int[]{2, 2, 3}, 1));
    }

}
