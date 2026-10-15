package org.lyndon.algo.array;

import java.util.*;

/**
 * LC49
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * <p>
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Explanation:
 * <p>
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 * Example 2:
 * <p>
 * Input: strs = [""]
 * <p>
 * Output: [[""]]
 * <p>
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * <p>
 * Output: [["a"]]
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] cnt = new int[26];
            for (char c : str.toCharArray()) {
                cnt[c - 'a']++;
            }
            String key = genKey(cnt);
            if (!map.containsKey(key)) {
                map.put(key, new LinkedList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private String genKey(int[] cnt) {
        StringBuilder sb = new StringBuilder();
        for (int c : cnt) {
            sb.append('#').append(c);
        }
        return sb.toString();
    }

}
