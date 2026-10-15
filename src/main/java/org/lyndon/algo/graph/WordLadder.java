package org.lyndon.algo.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LC127
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        Set<String> curLevel = new HashSet<>();
        curLevel.add(beginWord);
        int steps = 1;
        while (!curLevel.isEmpty()) {
            steps++;
            Set<String> nextLevel = new HashSet<>();
            dict.removeAll(curLevel);
            for (String word : curLevel) {
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char o = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[i] = c;
                        String s = new String(arr);
                        if (!dict.contains(s)) {
                            continue;
                        }
                        if (endWord.equals(s)) {
                            return steps;
                        }
                        nextLevel.add(s);
                    }
                    arr[i] = o;
                }
            }
            curLevel = nextLevel;
        }
        return 0;
    }

}
