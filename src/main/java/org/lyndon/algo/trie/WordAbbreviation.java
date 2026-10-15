package org.lyndon.algo.trie;

import java.util.*;

/**
 * LC527
 */
public class WordAbbreviation {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int cnt;

        void insert(String w) {
            TrieNode cur = this;
            for (int i = 0; i < w.length(); i++) {
                int c = w.charAt(i) - 'a';
                if (children[c] == null) {
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
                cur.cnt++;
            }
        }

        int uniquePrefixLen(String w) {
            TrieNode cur = this;
            for (int i = 0; i < w.length(); i++) {
                int c = w.charAt(i) - 'a';
                TrieNode node = cur.children[c];
                if (node.cnt == 1) {
                    return i + 1;
                }
            }
            return w.length();
        }
    }

    public List<String> wordsAbbreviation(List<String> words) {
        int n = words.size();
        String[] ans = new String[n];

        Map<String, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word = words.get(i);
            String key = word.length() + "#" + word.charAt(0) + "#" + word.charAt(word.length() - 1);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> group : groups.values()) {
            TrieNode root = new TrieNode();
            for (int id : group) {
                root.insert(words.get(id));
            }
            for (int id : group) {
                String word = words.get(id);
                ans[id] = abbreviate(word, root.uniquePrefixLen(word));
            }
        }
        return Arrays.asList(ans);
    }

    private String abbreviate(String w, int pre) {
        int omitted = w.length() - pre - 1;
        if (omitted <= 1) {
            return w;
        }
        return w.substring(0, pre) + omitted + w.charAt(w.length() - 1);
    }

}
