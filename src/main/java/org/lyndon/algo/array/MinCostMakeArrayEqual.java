package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC2448
 * You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
 * <p>
 * You can do the following operation any number of times:
 * <p>
 * Increase or decrease any element of the array nums by 1.
 * The cost of doing one operation on the ith element is cost[i].
 * <p>
 * Return the minimum total cost such that all the elements of the array nums become equal.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,5,2], cost = [2,3,1,14]
 * Output: 8
 * Explanation: We can make all the elements equal to 2 in the following way:
 * - Increase the 0th element one time. The cost is 2.
 * - Decrease the 1st element one time. The cost is 3.
 * - Decrease the 2nd element three times. The cost is 1 + 1 + 1 = 3.
 * The total cost is 2 + 3 + 3 = 8.
 * It can be shown that we cannot make the array equal with a smaller cost.
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2], cost = [4,2,8,1,3]
 * Output: 0
 * Explanation: All the elements are already equal, so no operations are needed.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length == cost.length
 * 1 <= n <= 105
 * 1 <= nums[i], cost[i] <= 106
 * Test cases are generated in a way that the output doesn't exceed 253
 */
public class MinCostMakeArrayEqual {

    public long minCost(int[] nums, int[] cost) {
        int[][] pairs = new int[nums.length][2];
        long total = 0;
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new int[]{nums[i], cost[i]};
            total += cost[i];
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        long sum = 0;
        int target = -1;
        for (int[] pair : pairs) {
            sum += pair[1];
            if (sum * 2 >= total) {
                target = pair[0];
                break;
            }
        }
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += Math.abs(nums[i] - target) * (long) cost[i];
        }
        return ans;
    }

}
