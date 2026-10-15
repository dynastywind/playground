package org.lyndon.algo.dp;

/**
 * LC1340
 */
public class JumpGameV {

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(arr, n, d, i, dp));
        }
        return ans;
    }

    private int dfs(int[] arr, int n, int d, int i, int[] dp) {
        if (dp[i] != 0) {
            return dp[i];
        }
        int best = 1;
        for (int j = i + 1; j <= Math.min(i + d, n - 1); j++) {
            if (arr[j] >= arr[i]) {
                break;
            }
            best = Math.max(best, dfs(arr, n, d, j, dp) + 1);
        }
        for (int j = i - 1; j >= Math.max(0, i - d); j--) {
            if (arr[j] >= arr[i]) {
                break;
            }
            best = Math.max(best, dfs(arr, n, d, j, dp) + 1);
        }
        dp[i] = best;
        return best;
    }

}
