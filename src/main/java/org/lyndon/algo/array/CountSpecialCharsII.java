package org.lyndon.algo.array;

import java.util.HashSet;
import java.util.Set;

/**
 * LC3121
 */
public class CountSpecialCharsII {

    public int numberOfSpecialChars(String word) {
        Set<Character> firstOccur = new HashSet<>();
        Set<Character> candidates = new HashSet<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                if (!firstOccur.contains(c)) {
                    if (candidates.contains(Character.toLowerCase(c))) {
                        set.add(Character.toLowerCase(c));
                    }
                    firstOccur.add(c);
                }
            } else if (Character.isLowerCase(c)) {
                if (firstOccur.contains(Character.toUpperCase(c))) {
                    set.remove(c);
                } else {
                    candidates.add(c);
                }
            }
        }
        return set.size();
    }

}
