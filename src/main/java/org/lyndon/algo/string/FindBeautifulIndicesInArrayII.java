package org.lyndon.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * LC3008
 */
public class FindBeautifulIndicesInArrayII {

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> posA = kmp(s, a);
        List<Integer> posB = kmp(s, b);

        List<Integer> res = new ArrayList<>();
        int j = 0;
        for (int i : posA) {
            while (j < posB.size() && posB.get(j) < i - k) {
                j++;
            }
            if (j < posB.size() && posB.get(j) <= i + k) {
                res.add(i);
            }
        }
        return res;
    }

    private List<Integer> kmp(String s, String p) {
        int n = s.length();
        int m = p.length();
        List<Integer> res = new ArrayList<>();
        if (n < m) {
            return res;
        }

        int[] next = new int[m];
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = next[j - 1];
            }
            if (p.charAt(i) == p.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            }
            if (j == m) {
                res.add(i - m + 1);
                j = next[j - 1];
            }
        }
        return res;
    }

}
