package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC1531
 */
public class StringCompressionII {

    public int getLengthOfOptimalCompression(String s, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int n = s.length();
        // min length of s[0..i-1] with k deletion
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // remove s[i]
                if (j < k) {
                    dp[i + 1][j + 1] = Math.min(dp[i][j], dp[i + 1][j + 1]);
                }

                // RLE
                char c = s.charAt(i);
                int cnt = 0;
                int del = 0;
                for (int p = i; p < n; p++) {
                    if (s.charAt(p) == c) {
                        cnt++;
                    } else if (j + ++del > k) {
                        break;
                    }
                    dp[p + 1][j + del] = Math.min(dp[p + 1][j + del], dp[i][j] + cost(cnt));
                }
            }
        }

        int ans = INF;
        for (int j = 0; j <= k; j++) {
            ans = Math.min(ans, dp[n][j]);
        }
        return ans;
    }

    private int cost(int cnt) {
        if (cnt == 0) {
            return 0;
        }
        if (cnt == 1) {
            return 1;
        }
        if (cnt < 10) {
            return 2;
        }
        if (cnt < 100) {
            return 3;
        }
        return 4;
    }

}
