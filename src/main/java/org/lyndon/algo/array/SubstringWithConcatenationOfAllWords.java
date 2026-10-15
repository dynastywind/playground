package org.lyndon.algo.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC30
 */
public class SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int n = s.length();
        int m = words.length;
        int wl = words[0].length();
        if (n < (long) m * wl) {
            return result;
        }

        Map<String, Integer> dict = new HashMap<>();
        for (String word : words) {
            dict.merge(word, 1, Integer::sum);
        }

        for (int i = 0; i < wl; i++) {
            Map<String, Integer> map = new HashMap<>();
            int left = i;
            int valid = 0;
            for (int right = i; right + wl <= n; right += wl) {
                String word = s.substring(right, right + wl);
                if (!dict.containsKey(word)) {
                    map.clear();
                    valid = 0;
                    left = right + wl;
                    continue;
                }
                map.merge(word, 1, Integer::sum);
                valid++;
                while (map.get(word) > dict.get(word)) {
                    String out = s.substring(left, left + wl);
                    map.merge(out, -1, Integer::sum);
                    left += wl;
                    valid--;
                }
                if (valid == m) {
                    result.add(left);
                    String out = s.substring(left, left + wl);
                    map.merge(out, -1, Integer::sum);
                    left += wl;
                    valid--;
                }
            }
        }
        return result;
    }

}
