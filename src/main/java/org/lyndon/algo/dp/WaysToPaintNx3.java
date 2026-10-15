package org.lyndon.algo.dp;

/**
 * LC1411
 */
public class WaysToPaintNx3 {

    /*
        ABA: 3*2=6
        ABC: 3*2*1=6
        ABA -> ABA: 3
        ABA -> ABC: 2
        ABC -> ABA: 2
        ABC -> ABC: 2
        a' = 3*a+2*b
        b' = 2(a+b)
     */
    public int numOfWays(int n) {
        final int MOD = 1_000_000_007;
        long aba = 6;
        long abc = 6;
        for (int i = 2; i <= n; i++) {
            long nAba = (3 * aba + 2 * abc) % MOD;
            long nAbc = (2 * (aba + abc)) % MOD;
            aba = nAba;
            abc = nAbc;
        }
        return (int) ((aba + abc) % MOD);
    }

}
