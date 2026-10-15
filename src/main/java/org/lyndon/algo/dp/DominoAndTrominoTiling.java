package org.lyndon.algo.dp;

/**
 * LC790
 */
public class DominoAndTrominoTiling {

    public int numTilings(int n) {
        final int MOD = 1_000_000_007;
        long[] full = new long[n + 1];
        long[] half = new long[n + 1];
        full[0] = 1;
        full[1] = 1;
        half[1] = 1;
        for (int i = 2; i <= n; i++) {
            full[i] = (full[i - 1] + full[i - 2] + half[i - 2] * 2) % MOD;
            half[i] = (full[i - 1] + half[i - 1]) % MOD;
        }
        return (int) full[n];
    }

}
