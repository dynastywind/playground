package org.lyndon.algo.dp;

/**
 * LC1269
 */
public class NumberOfWaysToStay {

    /*
        ways to stay at i after s steps
        dp[i][s]

        dp[0][0] = 1
        dp[1][1] = 1
        dp[min(arrLen - 1, steps)][steps] = 1

        dp[i][s] = dp[i][s - 1] + dp[i - 1][s - 1] + dp[i + 1][s - 1]

        dp[0][steps]
     */
    public int numWays(int steps, int arrLen) {
        final int MOD = 1_000_000_007;
        int max = Math.min(arrLen - 1, steps);
        int[] dp = new int[max + 1];
        dp[0] = 1;
        for (int j = 1; j <= steps; j++) {
            int[] next = new int[max + 1];
            for (int i = 0; i <= max; i++) {
                long a = (i > 0) ? dp[i - 1] : 0;
                long b = (i < max) ? dp[i + 1] : 0;
                next[i] = (int) ((a + b + dp[i]) % MOD);
            }
            dp = next;
        }
        return dp[0];
    }

    static void main() {
        var solution = new NumberOfWaysToStay();
        System.out.println(solution.numWays(3, 2));
        System.out.println(solution.numWays(2, 4));
        System.out.println(solution.numWays(4, 2));
    }

}
