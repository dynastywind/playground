package org.lyndon.algo.dp;

/**
 * LC639
 */
public class DecodeWaysII {

    public int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }

        final int MOD = 1_000_000_007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            if (i < n - 1 && s.charAt(i + 1) == '0') {
                continue;
            }
            char c = s.charAt(i);
            if (c == '*') {
                dp[i + 1] = (dp[i] * 9) % MOD;
                if (i > 0) {
                    char prev = s.charAt(i - 1);
                    if (prev == '*') {
                        dp[i + 1] = (dp[i + 1] + dp[i - 1] * 15) % MOD;
                    } else if (prev == '1') {
                        dp[i + 1] = (dp[i + 1] + dp[i - 1] * 9) % MOD;
                    } else if (prev == '2') {
                        dp[i + 1] = (dp[i + 1] + dp[i - 1] * 6) % MOD;
                    }
                }
            } else if (c >= '1' && c <= '9') {
                dp[i + 1] = dp[i];
                if (i > 0) {
                    char prev = s.charAt(i - 1);
                    if (prev == '*') {
                        dp[i + 1] = (dp[i + 1] + dp[i - 1] * (c <= '6' ? 2 : 1)) % MOD;
                    } else if (prev == '1' || (prev == '2' && c <= '6')) {
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % MOD;
                    }
                }
            } else {
                char prev = s.charAt(i - 1);
                if (prev == '*') {
                    dp[i + 1] = (dp[i - 1] * 2) % MOD;
                } else if (prev == '1' || prev == '2') {
                    dp[i + 1] = dp[i - 1];
                }
            }
        }
        return (int) dp[n];
    }

    static void main() {
        var solution = new DecodeWaysII();
        System.out.println(solution.numDecodings("11"));
        System.out.println(solution.numDecodings("1*"));
        System.out.println(solution.numDecodings("***"));
        System.out.println(solution.numDecodings("2*"));
        System.out.println(solution.numDecodings("**0"));
        System.out.println(solution.numDecodings("210"));
    }

}
