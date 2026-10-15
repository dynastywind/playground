package org.lyndon.algo.tree;

/**
 * LC96
 */
public class UniqueBST {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int r = 1; r <= i; r++) {
                dp[i] += dp[r - 1] * dp[i - r];
            }
        }
        return dp[n];
    }

}
