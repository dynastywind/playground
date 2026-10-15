package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC76
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 */
public class MinWindowSubstring {

    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.merge(c, 1, Integer::sum);
        }

        Map<Character, Integer> window = new HashMap<>();
        int start = 0;
        int len = Integer.MAX_VALUE;
        int valid = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (need.containsKey(c)) {
                window.merge(c, 1, Integer::sum);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (valid == need.size()) {
                if (right - left + 1 < len) {
                    start = left;
                    len = right - left + 1;
                }
                char ch = s.charAt(left++);
                if (need.containsKey(ch)) {
                    if (window.get(ch).equals(need.get(ch))) {
                        valid--;
                    }
                    window.merge(ch, -1, Integer::sum);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        var solution = new MinWindowSubstring();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }

}
