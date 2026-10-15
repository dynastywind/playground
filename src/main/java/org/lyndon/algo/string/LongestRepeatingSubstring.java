package org.lyndon.algo.string;

/**
 * LC 1062
 * <p>
 * Given a string s, return the length of the longest repeating substrings. If no repeating substring exists, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcd"
 * Output: 0
 * Explanation: There is no repeating substring.
 * Example 2:
 * <p>
 * Input: s = "abbaba"
 * Output: 2
 * Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
 * Example 3:
 * <p>
 * Input: s = "aabcaabdaab"
 * Output: 3
 * Explanation: The longest repeating substring is "aab", which occurs 3 times.
 */
public class LongestRepeatingSubstring {

    /*
          ~ a b b a b a
        ~ 0 0 0 0 0 0 0
        a 0 0 0 0 1 0 1
        b 0 0 0 1 0 2 0
        b 0 0 1 0 0 1 0
        a 0 1 0 0 0 0 2
        b 0 0 2 1 0 0 0
        a 0 1 0 0 2 0 0
        dp[i][j] = length of common suffix of s[i] and s[j]
     */
    public int longestRepeatingSubstring(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        int max = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        var solution = new LongestRepeatingSubstring();
        System.out.println(solution.longestRepeatingSubstring("abcd"));
        System.out.println(solution.longestRepeatingSubstring("abbaba"));
        System.out.println(solution.longestRepeatingSubstring("aabcaabdaab"));
        System.out.println(solution.longestRepeatingSubstring("aaaaa"));
        System.out.println(solution.longestRepeatingSubstring("banana"));
    }

}
