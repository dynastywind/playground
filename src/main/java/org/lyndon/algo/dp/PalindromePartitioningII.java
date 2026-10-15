package org.lyndon.algo.dp;

/**
 * LC132
 */
public class PalindromePartitioningII {

    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }
        char[] cs = s.toCharArray();
        boolean[][] palindrome = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (cs[i] == cs[j] && (j - i < 2 || palindrome[i + 1][j - 1])) {
                    palindrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[n + 1];
        dp[0] = -1;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (palindrome[j][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }

}
