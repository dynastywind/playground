package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC126
 */
public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }

        Map<String, List<String>> parents = new HashMap<>();
        Set<String> curLevel = new HashSet<>();
        curLevel.add(beginWord);
        boolean found = false;

        while (!curLevel.isEmpty()) {
            if (found) {
                break;
            }
            dict.removeAll(curLevel);
            Set<String> nextLevel = new HashSet<>();
            for (String word : curLevel) {
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];
                    for (int j = 0; j < 26; j++) {
                        char c = (char) (j + 'a');
                        if (c == old) {
                            continue;
                        }
                        arr[i] = c;
                        String next = new String(arr);
                        if (!dict.contains(next)) {
                            continue;
                        }
                        nextLevel.add(next);
                        parents.computeIfAbsent(next, k -> new ArrayList<>()).add(word);
                        if (next.equals(endWord)) {
                            found = true;
                        }
                    }
                    arr[i] = old;
                }
            }
            curLevel = nextLevel;
        }

        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(parents, endWord, beginWord, path, res);
        }
        return res;
    }

    private void dfs(Map<String, List<String>> parents, String word, String beginWord, List<String> path, List<List<String>> res) {
        if (word.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String parent : parents.get(word)) {
            path.addFirst(parent);
            dfs(parents, parent, beginWord, path, res);
            path.removeFirst();
        }
    }

}
