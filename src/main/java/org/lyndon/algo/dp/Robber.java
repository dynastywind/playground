package org.lyndon.algo.dp;

public class Robber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        return dp[nums.length];
    }

    public static void main(String[] args) {
        System.out.println(new Robber().rob(new int[]{2, 7, 9, 3, 1}));
    }

}
