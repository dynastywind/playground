package org.lyndon.algo.dp;

/**
 * LC1621
 */
public class NonOverlappingLineSegments {

    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;
        long[][] dp0 = new long[n][k + 1];
        long[][] dp1 = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            dp0[i][0] = 1;
        }
        /*
            dp0[i][j] = dp0[i-1][j] + dp1[i-1][j]  i is not right endpoint of jth line
            dp1[i][j] = dp1[i-1][j] + (dp0[i-1][j-1] + dp1[i-1][j-1])    i is right endpoint of jth line
         */
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp0[i][j] = (dp0[i - 1][j] + dp1[i - 1][j]) % MOD;

                dp1[i][j] = dp1[i - 1][j];
                if (j > 0) {
                    dp1[i][j] = (dp1[i][j] + dp0[i - 1][j - 1] + dp1[i - 1][j - 1]) % MOD;
                }
            }
        }
        return (int) ((dp0[n - 1][k] + dp1[n - 1][k]) % MOD);
    }

}
