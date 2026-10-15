package org.lyndon.algo.backtrack;

import java.util.Arrays;

/**
 * LC698
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 16
 * 1 <= nums[i] <= 104
 * The frequency of each element is in the range [1, 4]
 */
public class PartitionKEqualSum {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (max > target) {
            return false;
        }
        return dfs(nums, new boolean[nums.length], 0, target, k, 0, nums.length - 1);
    }

    private boolean dfs(int[] nums, boolean[] used, int sum, int target, int k, int bucket, int start) {
        if (bucket == k - 1) {
            return true;
        }
        if (sum == target) {
            return dfs(nums, used, 0, target, k, bucket + 1, nums.length - 1);
        }

        for (int i = start; i >= 0; i--) {
            if (used[i]) {
                continue;
            }
            if (sum + nums[i] > target) {
                continue;
            }
            used[i] = true;
            if (dfs(nums, used, sum + nums[i], target, k, bucket, i - 1)) {
                return true;
            }
            used[i] = false;
            while (i > 0 && nums[i - 1] == nums[i]) {
                i--;
            }
        }
        return false;
    }

}
