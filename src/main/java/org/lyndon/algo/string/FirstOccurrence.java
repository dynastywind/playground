package org.lyndon.algo.string;

/**
 * LC28
 */
public class FirstOccurrence {

    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        int[] next = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        j = 0;
        for (int i = 0; i < m; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == n) {
                return i - n + 1;
            }
        }
        return -1;
    }

}
