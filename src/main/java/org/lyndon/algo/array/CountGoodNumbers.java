package org.lyndon.algo.array;

/**
 * LC1922
 */
public class CountGoodNumbers {

    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long even = (n + 1) >> 1;
        long odd = n >> 1;
        return (int) (pow(5, even) * pow(4, odd) % MOD);
    }

    private long pow(long base, long exp) {
        long res = 1L;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = res * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }

}
