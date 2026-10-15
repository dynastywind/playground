package org.lyndon.algo.dp;

/**
 * LC3489
 */
public class ZeroArrayTransformIV {

    public int minZeroArray(int[] nums, int[][] queries) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];
            if (target == 0) {
                continue;
            }

            boolean[] dp = new boolean[target + 1];
            dp[0] = true;
            int k = -1;
            for (int j = 0; j < queries.length; j++) {
                int l = queries[j][0];
                int r = queries[j][1];
                int v = queries[j][2];
                if (i < l || i > r || v > target) {
                    continue;
                }
                for (int s = target; s >= v; s--) {
                    dp[s] |= dp[s - v];
                }
                if (dp[target]) {
                    k = j + 1;
                    break;
                }
            }

            if (k == -1) {
                return -1;
            }
            ans = Math.max(ans, k);
        }
        return ans;
    }

}
