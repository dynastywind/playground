package org.lyndon.algo.dp;

/**
 * LC213
 */
public class HouseRobberII {

    public int rob(int[] nums) {
        return Math.max(nums[0] + rob(nums, 2, nums.length - 1), rob(nums, 1, nums.length));
    }

    // [start, end)
    private int rob(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int[] dp = new int[end - start + 1];
        dp[1] = nums[start];
        for (int i = 1; i < dp.length - 1; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i + start]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        var solution = new HouseRobberII();
        System.out.println(solution.rob(new int[]{2, 3, 2}));
    }

}
