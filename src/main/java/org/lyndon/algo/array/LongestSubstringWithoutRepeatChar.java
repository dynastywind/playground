package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC3
 * Given a string s, find the length of the longest substring without duplicate characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatChar {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        Map<Character, Integer> pos = new HashMap<>();
        pos.put(s.charAt(0), 0);
        int ans = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            pos.merge(c, 1, Integer::sum);
            while (pos.get(c) > 1) {
                char ch = s.charAt(left++);
                pos.merge(ch, -1, Integer::sum);
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new LongestSubstringWithoutRepeatChar();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }

}
