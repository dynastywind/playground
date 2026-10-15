package org.lyndon.algo.dp;

/**
 * LC10
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * Return a boolean indicating whether the matching covers the entire input string (not partial).
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * <p>
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
//        if (p.isEmpty()) {
//            return s.isEmpty();
//        }
//        boolean match = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
//        if (p.length() >= 2 && p.charAt(1) == '*') {
//            return (match && isMatch(s.substring(1), p)) || isMatch(s, p.substring(2));
//        } else {
//            return match && isMatch(s.substring(1), p.substring(1));
//        }
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p, memo);
    }

    private boolean dp(int i, int j, String s, String p, Boolean[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (j == p.length()) {
            memo[i][j] = i == s.length();
            return memo[i][j];
        }
        boolean match = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            memo[i][j] = dp(i, j + 2, s, p, memo) || (match && dp(i + 1, j, s, p, memo));
        } else {
            memo[i][j] = match && dp(i + 1, j + 1, s, p, memo);
        }
        return memo[i][j];
    }

}
