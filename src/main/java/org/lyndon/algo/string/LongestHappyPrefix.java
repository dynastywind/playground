package org.lyndon.algo.string;

/**
 * LC1392
 */
public class LongestHappyPrefix {

    public String longestPrefix(String s) {
        int n = s.length();
        int[] next = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        int max = next[n - 1];
        return max == 0 ? "" : s.substring(n - max);
    }

}
