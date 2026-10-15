package org.lyndon.algo.array;

/**
 * LC1201
 * An ugly number is a positive integer that is divisible by a, b, or c.
 * <p>
 * Given four integers n, a, b, and c, return the nth ugly number.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, a = 2, b = 3, c = 5
 * Output: 4
 * Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 * Example 2:
 * <p>
 * Input: n = 4, a = 2, b = 3, c = 4
 * Output: 6
 * Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
 * Example 3:
 * <p>
 * Input: n = 5, a = 2, b = 11, c = 13
 * Output: 10
 * Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
 */
public class UglyNumberIII {

    private static final long BOUND = 2_000_000_001L;

    public int nthUglyNumber(int n, int a, int b, int c) {
        long ab = lcm(a, b);
        long ac = lcm(a, c);
        long bc = lcm(b, c);
        long abc = lcm(ab, c);

        long lo = Math.min(a, Math.min(b, c));
        long hi = 2_000_000_000L;
        while (lo < hi) {
            long mid = (lo + hi) >> 1;
            if (count(mid, a, b, c, ab, ac, bc, abc) >= n) {
                hi  = mid;
            } else {
                lo = mid + 1;
            }
        }
        return (int) lo;
    }

    private long count(long x, long a, long b, long c, long ab, long ac, long bc, long abc) {
        return x / a + x / b + x / c - x / ab - x / ac - x / bc + x / abc;
    }

    private long lcm(long x, long y) {
        long g = gcd(x, y);
        long q = x / g;
        if (q > BOUND / y) {
            return BOUND;
        }
        return Math.min(BOUND, q * y);
    }

    private long gcd(long x, long y) {
        return y == 0 ? x : gcd(y, x % y);
    }

}
