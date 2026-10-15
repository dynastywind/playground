package org.lyndon.algo.string;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                end = i + 1;
            }
        }
        for (int diff = 2; diff < s.length(); diff++) {
            for (int i = 0; i < s.length() - diff; i++) {
                int j = i + diff;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        var solution = new LongestPalindromicSubstring();
        System.out.println(solution.longestPalindrome("babad"));
        System.out.println(solution.longestPalindrome("bb"));
    }

}
