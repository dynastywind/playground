package org.lyndon.algo.dp;

/**
 * LC198
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int n = nums.length;
        int a = 0;
        int b = nums[0];
        for (int i = 1; i < n; i++) {
            int c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        return b;
    }

}
