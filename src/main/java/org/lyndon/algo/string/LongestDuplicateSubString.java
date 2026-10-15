package org.lyndon.algo.string;

import java.util.HashSet;
import java.util.Set;

/**
 * LC1044
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
 * <p>
 * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".
 * <p>
 * Example 1:
 * <p>
 * Input: s = "banana"
 * Output: "ana"
 * Example 2:
 * <p>
 * Input: s = "abcd"
 * Output: ""
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= s.length <= 3 * 104
 * s consists of lowercase English letters.
 */
public class LongestDuplicateSubString {

    private static final long MOD1 = 1_000_000_007L, MOD2 = 998_244_353L;
    private static final long BASE1 = 131L, BASE2 = 137L;

    public String longestDupSubstring(String s) {
        final int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - 'a' + 1;
        }
        int low = 1, high = n - 1, ansStart = -1, ansLen = 0;
        while (low <= high) {
            int L = (low + high) >>> 1;
            int start = check(a, L);
            if (start != -1) {
                ansStart = start;
                ansLen = L;
                low = L + 1;
            } else {
                high = L - 1;
            }
        }
        return ansStart == -1 ? "" : s.substring(ansStart, ansStart + ansLen);
    }

    private int check(int[] a, int L) {
        final int n = a.length;
        long h1 = 0, h2 = 0, pow1 = 1, pow2 = 1;
        for (int i = 0; i < L; i++) {
            h1 = hash(h1, a[i], BASE1, MOD1);
            h2 = hash(h2, a[i], BASE2, MOD2);
            if (i < L - 1) {
                pow1 = pow(pow1, BASE1, MOD1);
                pow2 = pow(pow2, BASE2, MOD2);
            }
        }
        Set<Long> seen = new HashSet<>();
        seen.add(combineHash(h1, h2));
        for (int i = L; i < n; i++) {
            h1 = updateHash(h1, a[i - L], a[i], pow1, BASE1, MOD1);
            h2 = updateHash(h2, a[i - L], a[i], pow2, BASE2, MOD2);
            long h = combineHash(h1, h2);
            if (seen.contains(h)) {
                return i - L + 1;
            } else {
                seen.add(h);
            }
        }
        return -1;
    }

    // hash[s] = (s[0] * base^(m - 1) + s[1] * base^(m - 2) + ... + s[m - 1] * base^0) % MOD
    private long hash(long h, int a, long base, long mod) {
        return (h * base + a) % mod;
    }

    private long pow(long pow, long base, long mod) {
        return (pow * base) % mod;
    }

    // h = ((h - T[i] * base^(m - 1)) * base + T[i + m]) % MOD
    private long updateHash(long h, int a1, int a2, long pow, long base, long mod) {
        return ((h - a1 * pow % mod + mod) % mod * base + a2) % mod;
    }

    private long combineHash(long h1, long h2) {
        return (h1 * MOD2) + h2;
    }

    public static void main(String[] args) {
        var solution = new LongestDuplicateSubString();
        System.out.println(solution.longestDupSubstring("banana"));
        System.out.println(solution.longestDupSubstring("aa"));
    }

}
