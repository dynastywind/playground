package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC1099
 * Given an array nums of integers and integer k, return the maximum sum such that there exists i < j with nums[i] + nums[j] = sum and sum < k. If no i, j exist satisfying this equation, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [34,23,1,24,75,33,54,8], k = 60
 * Output: 58
 * Explanation: We can use 34 and 24 to sum 58 which is less than 60.
 * Example 2:
 * <p>
 * Input: nums = [10,20,30], k = 15
 * Output: -1
 * Explanation: In this case it is not possible to get a pair sum less that 15.
 */
public class TwoSumLessThanK {

    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            if (nums[low] + nums[high] < k) {
                ans = Math.max(ans, nums[low] + nums[high]);
                low++;
            } else {
                high--;
            }
        }
        return ans;
    }

}
