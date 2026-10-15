package org.lyndon.algo.array;

/**
 * LC866
 * Given an integer n, return the smallest prime palindrome greater than or equal to n.
 * <p>
 * An integer is prime if it has exactly two divisors: 1 and itself. Note that 1 is not a prime number.
 * <p>
 * For example, 2, 3, 5, 7, 11, and 13 are all primes.
 * An integer is a palindrome if it reads the same from left to right as it does from right to left.
 * <p>
 * For example, 101 and 12321 are palindromes.
 * The test cases are generated so that the answer always exists and is in the range [2, 2 * 108].
 * <p>
 * Example 1:
 * <p>
 * Input: n = 6
 * Output: 7
 * Example 2:
 * <p>
 * Input: n = 8
 * Output: 11
 * Example 3:
 * <p>
 * Input: n = 13
 * Output: 101
 * <p>
 * Constraints:
 * 1 <= n <= 108
 */
public class PrimePalindrome {

    public int primePalindrome(int n) {
        if (n <= 11) {
            for (int x = n; x <= 11; x++) {
                if (isPalindrome(x) && isPrime(x)) {
                    return x;
                }
            }
        }
        // odd digits only after 11
        for (int root = 1; root < 100000; root++) {
            String s = Integer.toString(root);
            StringBuilder sb = new StringBuilder(s);
            for (int i = s.length() - 2; i >= 0; i--) {
                sb.append(s.charAt(i));
            }
            int cand = Integer.parseInt(sb.toString());
            if (cand >= n && isPrime(cand)) {
                return cand;
            }
        }
        return -1;
    }

    private boolean isPalindrome(int x) {
        int t = x;
        int rev = 0;
        while (t > 0) {
            rev = rev * 10 + t % 10;
            t /= 10;
        }
        return rev == x;
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        if (x % 2 == 0) {
            return x == 2;
        }
        for (int i = 3; (long) i * i <= x; i += 2) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

}
