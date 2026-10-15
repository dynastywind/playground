package org.lyndon.algo.dp;

/**
 * LC494
 * <p>
 * You are given an integer array nums and an integer target.
 * <p>
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 * <p>
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * Example 2:
 * <p>
 * Input: nums = [1], target = 1
 * Output: 1
 */
public class TargetSum {

    private int count;

    /*
        sigma +: P
        sigma |-|: Q
        P - Q = target
        P + Q = sum
        P = (sum + target) / 2
     */
    public int findTargetSumWays(int[] nums, int target) {
//        dfs(nums, 0, 0, target);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (target > sum || target < -sum || ((sum + target) & 1) == 1) {
            return 0;
        }
        int goal = (sum + target) / 2;
        int[] dp = new int[goal + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = goal; j >= num; j--) {
                dp[j] = dp[j] + dp[j - num];
            }
        }
        return dp[goal];
    }

    private void dfs(int[] nums, int i, int sum, int target) {
        if (i == nums.length) {
            if (sum == target) {
                count++;
            }
            return;
        }
        dfs(nums, i + 1, sum + nums[i], target);
        dfs(nums, i + 1, sum - nums[i], target);
    }

}
