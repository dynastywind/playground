package org.lyndon.algo.dp;

/**
 * LC91
 * You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
 * <p>
 * "1" -> 'A'
 * <p>
 * "2" -> 'B'
 * <p>
 * ...
 * <p>
 * "25" -> 'Y'
 * <p>
 * "26" -> 'Z'
 * <p>
 * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
 * <p>
 * For example, "11106" can be decoded into:
 * <p>
 * "AAJF" with the grouping (1, 1, 10, 6)
 * "KJF" with the grouping (11, 10, 6)
 * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 * Note: there may be strings that are impossible to decode.
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 */
public class DecodeWays {

    /*
        dp[i] = dp[i - 1]: s[i - 1] is a non-zero single digit
        dp[i] += dp[i - 2]: s[i - 2..i - 1] is a valid two-digit number
        dp[0] = 1
        dp[1] = 1 if s[0] != '0'
     */
    public int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            int d = Integer.parseInt(s.substring(i - 2, i));
            if (d >= 10 && d <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

}
