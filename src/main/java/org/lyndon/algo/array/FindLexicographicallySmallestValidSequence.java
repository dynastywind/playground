package org.lyndon.algo.array;

/**
 * LC3302
 */
public class FindLexicographicallySmallestValidSequence {

    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] suf = new int[n + 1];
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word2.charAt(j) == word1.charAt(i)) {
                j--;
            }
            suf[i] = m - 1 - j;
        }

        int[] ans = new int[m];
        boolean used = false;
        int k = 0;
        for (int i = 0; i < n && k < m; i++) {
            if (word1.charAt(i) == word2.charAt(k)) {
                ans[k++] = i;
            } else if (!used && suf[i + 1] >= m - 1 - k) {
                used = true;
                ans[k++] = i;
            }
        }
        return k == m ? ans : new int[0];
    }

}
