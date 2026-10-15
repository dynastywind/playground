package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC340
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 * Example 2:
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa" with length 2.
 */
public class LengthOfSubstringWithAtMostKDistinctChars {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.merge(c, 1, Integer::sum);
            if (map.size() > k) {
                ans = Math.max(ans, i - left);
                while (map.size() > k) {
                    char l = s.charAt(left++);
                    map.merge(l, -1, Integer::sum);
                    if (map.get(l) <= 0) {
                        map.remove(l);
                    }
                }
            }
        }
        if (map.size() <= k) {
            ans = Math.max(ans, s.length() - left);
        }
        return ans;
    }

}
