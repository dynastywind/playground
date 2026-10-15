package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC259
 * <p>
 * Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,0,1,3], target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 * Example 2:
 * <p>
 * Input: nums = [], target = 0
 * Output: 0
 * Example 3:
 * <p>
 * Input: nums = [0], target = 0
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 0 <= n <= 3500
 * -100 <= nums[i] <= 100
 * -100 <= target <= 100
 */
public class ThreeSumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            cnt += twoSmaller(nums, i + 1, target - nums[i]);
        }
        return cnt;
    }

    private int twoSmaller(int[] nums, int start, int target) {
        int cnt = 0;
        int low = start;
        int high = nums.length - 1;
        while (low < high) {
            if (nums[low] + nums[high] < target) {
                cnt += high - low;
                low++;
            } else {
                high--;
            }
        }
        return cnt;
    }

}
