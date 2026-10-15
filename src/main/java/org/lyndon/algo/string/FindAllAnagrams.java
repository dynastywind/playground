package org.lyndon.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC438
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            map.merge(p.charAt(i), 1, Integer::sum);
        }
        int left = 0;
        int valid = 0;
        List<Integer> ans = new ArrayList<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (!map.containsKey(c)) {
                left = right + 1;
                valid = 0;
                count.clear();
                continue;
            }
            if (count.merge(c, 1, Integer::sum).equals(map.get(c))) {
                valid++;
            } else {
                while (count.get(c) > map.get(c)) {
                    char l = s.charAt(left);
                    if (count.get(l).equals(map.get(l))) {
                        valid--;
                    }
                    count.merge(l, -1, Integer::sum);
                    left++;
                }
            }
            if (valid == map.size()) {
                ans.add(left);
            }
        }
        return ans;
    }

}
