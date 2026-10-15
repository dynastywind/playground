package org.lyndon.algo.dp;

/**
 * LC400
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 * <p>
 * Return the minimized largest sum of the split.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= k <= min(50, nums.length)
 */
public class SplitArrayLargestSum {

    private int min = Integer.MAX_VALUE;

    public int splitArray(int[] nums, int k) {
        min = Integer.MAX_VALUE;
        dfs(nums, k, new boolean[nums.length - 1], 0, 0);
        return min;
    }

    private void dfs(int[] nums, int k, boolean[] split, int index, int level) {
        if (level == k - 1) {
            int sum = 0;
            int max = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                sb.append(nums[i]);
                sum += nums[i];
                if (i < nums.length - 1 && split[i]) {
                    sb.append(";");
                    max = Math.max(max, sum);
                    sum = 0;
                } else if (i != nums.length - 1) {
                    sb.append(",");
                }
            }
            max = Math.max(max, sum);
            System.out.println(sb);
            min = Math.min(max, min);
            return;
        }
        for (int i = index; i < nums.length - 1; i++) {
            split[i] = true;
            dfs(nums, k, split, i + 1, level + 1);
            split[i] = false;
        }
    }

    public static void main(String[] args) {
        var solution = new SplitArrayLargestSum();
        System.out.println(solution.splitArray(new int[]{1, 2, 3, 4, 5}, 2));
        System.out.println(solution.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

}
