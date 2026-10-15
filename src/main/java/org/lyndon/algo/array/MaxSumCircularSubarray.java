package org.lyndon.algo.array;

/**
 * LC918
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 * <p>
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 * <p>
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 * Example 2:
 * <p>
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 * Example 3:
 * <p>
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 */
public class MaxSumCircularSubarray {

    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int curMax = 0;
        int minSum = Integer.MAX_VALUE;
        int curMin = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
            curMax += nums[i];
            if (curMax < 0) {
                curMax = 0;
            }
            maxSum = Math.max(maxSum, curMax);

            curMin += nums[i];
            if (curMin > 0) {
                curMin = 0;
            }
            minSum = Math.min(minSum, curMin);
        }
        if (max < 0) {
            return max;
        }
        return Math.max(maxSum, sum - minSum);
    }

    public static void main(String[] args) {
        var solution = new MaxSumCircularSubarray();
        System.out.println(solution.maxSubarraySumCircular(new int[]{1, -2, 3, -2}));
        System.out.println(solution.maxSubarraySumCircular(new int[]{5, -3, 5}));
        System.out.println(solution.maxSubarraySumCircular(new int[]{-10, -7, 9, -7, 6, 9, -9, -4, -8, -5}));
        System.out.println(solution.maxSubarraySumCircular(new int[]{-10, -8}));
    }

}
