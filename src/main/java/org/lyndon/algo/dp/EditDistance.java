package org.lyndon.algo.dp;

/**
 * LC72 EditDistance
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class EditDistance {

    /*
      ~ r o s
    ~ 0 1 2 3
    h 1 1 2 3
    o 2 2 1 2
    r 3 2 2 2
    s 4 3 3 2
    e 5 4 4 3
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public int minDistance2(String word1, String word2) {
        int[] dp = new int[word2.length() + 1];
        for (int i = 0; i <= word2.length(); i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            int prev = dp[0];
            dp[0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                int tmp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev;
                } else {
                    dp[j] = Math.min(Math.min(dp[j], prev), dp[j - 1]) + 1;
                }
                prev = tmp;
            }
        }
        return dp[word2.length()];
    }

}
