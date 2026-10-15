package org.lyndon.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC1520
 * Valid intervals are either non-overlapping or nested within each other
 */
public class MaxNumberNonOverlappingSubstrings {

    public List<String> maxNumOfSubstrings(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        int prevRight = -1;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (i != first[c]) {
                continue;
            }
            int r = extend(s, i, first, last);
            if (r == -1) {
                continue;
            }
            String sub = s.substring(i, r + 1);
            if (i <= prevRight) {
                result.removeLast();
            }
            result.add(sub);
            prevRight = r;
        }
        return result;
    }

    private int extend(String s, int i, int[] first, int[] last) {
        int r = last[s.charAt(i) - 'a'];
        for (int j = i; j <= r; j++) {
            int c = s.charAt(j) - 'a';
            if (first[c] < i) {
                return -1;
            }
            r = Math.max(r, last[c]);
        }
        return r;
    }

}
