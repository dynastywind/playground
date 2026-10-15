package org.lyndon.algo.dp;

/**
 * LC471
 */
public class EncodeString {

    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                String sub = s.substring(i, j + 1);
                dp[i][j] = sub;
                if (len < 5) {
                    continue;
                }

                for (int k = i; k < j; k++) {
                    String candidate = dp[i][k] + dp[k + 1][j];
                    if (candidate.length() < dp[i][j].length()) {
                        dp[i][j] = candidate;
                    }
                }

                // check repeat pattern
                int pos = (sub + sub).indexOf(sub, 1);
                if (pos >= sub.length()) {
                    continue;
                }
                int rep = sub.length() / pos;
                String unit = dp[i][i + pos - 1];
                String candidate = rep + "[" + unit + "]";
                if (candidate.length() < dp[i][j].length()) {
                    dp[i][j] = candidate;
                }
            }
        }
        return dp[0][n - 1];
    }

}
