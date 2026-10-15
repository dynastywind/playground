package org.lyndon.algo.string;

/**
 * LC 564
 * Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.
 * <p>
 * The closest is defined as the absolute difference minimized between two integers.
 * <p>
 * Example 1:
 * <p>
 * Input: n = "123"
 * Output: "121"
 * Example 2:
 * <p>
 * Input: n = "1"
 * Output: "0"
 * Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n.length <= 18
 * n consists of only digits.
 * n does not have leading zeros.
 * n is representing an integer in the range [1, 1018 - 1].
 */
public class ClosestPalindrome {

    public String nearestPalindromic(String n) {
        long m = Long.parseLong(n);
        int l = n.length();
        long half = pow10(l / 2);
        long p = m / half;
        boolean odd = l % 2 != 0;
        long[] candidates = new long[]{
                mirror(p - 1, odd),
                mirror(p, odd),
                mirror(p + 1, odd),
                pow10(l - 1) - 1,
                pow10(l) + 1
        };
        long minDiff = Long.MAX_VALUE;
        long ans = 0;
        for (long candidate : candidates) {
            if (candidate == m) {
                continue;
            }
            long diff = Math.abs(candidate - m);
            if (minDiff > diff) {
                minDiff = diff;
                ans = candidate;
            } else if (minDiff == diff && candidate < ans) {
                ans = candidate;
            }
        }
        return "" + ans;
    }

    private long mirror(long p, boolean odd) {
        long res = p;
        if (odd) {
            p /= 10;
        }
        while (p > 0) {
            res = res * 10 + p % 10;
            p /= 10;
        }
        return res;
    }

    private long pow10(int m) {
        long r = 1;
        while (m > 0) {
            r *= 10;
            m--;
        }
        return r;
    }

    public static void main(String[] args) {
        var solution = new ClosestPalindrome();
        System.out.println(solution.nearestPalindromic("2"));
    }

}
