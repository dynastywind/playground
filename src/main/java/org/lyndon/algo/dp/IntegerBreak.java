package org.lyndon.algo.dp;

/**
 * LC343
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                int best = Math.max(dp[j] * dp[i - j], j * (i - j));
                best = Math.max(best, dp[i - j] * j);
                best = Math.max(best, dp[j] * (i - j));
                dp[i] = Math.max(dp[i], best);
            }
        }
        return dp[n];
    }

    // 8 = 5 3
    //     2 6
    //     1 7
    //     4 4
    public static void main(String[] args) {
        var solution = new IntegerBreak();
        System.out.println(solution.integerBreak(8));
    }

}
