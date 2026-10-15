package org.lyndon.algo.string;

import java.util.HashMap;
import java.util.Map;

/**
 * LC828
 * Let's define a function countUniqueChars(s) that returns the number of unique characters in s.
 * <p>
 * For example, calling countUniqueChars(s) if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * Given a string s, return the sum of countUniqueChars(t) where t is a substring of s. The test cases are generated such that the answer fits in a 32-bit integer.
 * <p>
 * Notice that some substrings can be repeated so in this case you have to count the repeated ones too.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Every substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * Example 2:
 * <p>
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 * Example 3:
 * <p>
 * Input: s = "LEETCODE"
 * Output: 92
 */
public class CountUniqCharsOfAllSubstrings {

    public int uniqueLetterString(String s) {
        Map<Character, Integer> pos = new HashMap<>();
        Map<Character, Integer> prev = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (pos.containsKey(c)) {
                ans += (long) (pos.get(c) - prev.get(c)) * (i - pos.get(c));
                prev.put(c, pos.get(c));
            } else {
                prev.put(c, -1);
            }
            pos.put(c, i);
        }
        for (Map.Entry<Character, Integer> e : pos.entrySet()) {
            Character key = e.getKey();
            ans += (long) (pos.get(key) - prev.get(key)) * (s.length() - pos.get(key));
        }
        return (int) ans;
    }

}
