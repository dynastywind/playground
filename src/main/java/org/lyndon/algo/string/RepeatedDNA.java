package org.lyndon.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC187
 */
public class RepeatedDNA {

    public List<String> findRepeatedDnaSequences(String s) {
        final int L = 10;
        Map<String, Integer> cnt = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= s.length() - L; i++) {
            String sub = s.substring(i, i + L);
            int c = cnt.merge(sub, 1, Integer::sum);
            if (c == 2) {
                res.add(sub);
            }
        }
        return res;
    }

    public List<String> findRepeatedDnaSequencesMask(String s) {
        final int L = 10;
        final int MASK = (1 << (2 * L)) - 1;
        List<String> res = new ArrayList<>();
        int n = s.length();
        if (n <= L) {
            return res;
        }
        int[] code = new int[128];
        code['A'] = 0;
        code['C'] = 1;
        code['G'] = 2;
        code['T'] = 3;
        Map<Integer, Integer> cnt = new HashMap<>();
        int h = 0;
        for (int i = 0; i < n; i++) {
            h = ((h << 2) | code[s.charAt(i)]) & MASK;
            if (i >= L - 1) {
                int c = cnt.merge(h, 1, Integer::sum);
                if (c == 2) {
                    res.add(s.substring(i - L + 1, i + 1));
                }
            }
        }
        return res;
    }

}
