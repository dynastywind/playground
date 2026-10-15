package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidWordsForPuzzles {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); i++) {
                mask |= 1 << (word.charAt(i) - 'a');
            }
            if (Integer.bitCount(mask) <= 7) {
                cnt.merge(mask, 1, Integer::sum);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int start = 1 << (puzzle.charAt(0) - 'a');
            int and = 0;
            for (int i = 1; i < puzzle.length(); i++) {
                and |= 1 << (puzzle.charAt(i) - 'a');
            }
            int total = 0;
            for (int sub = and; ; sub = (sub - 1) & and) {
                total += cnt.getOrDefault(sub | start, 0);
                if (sub == 0) {
                    break;
                }
            }
            ans.add(total);
        }
        return ans;
    }

    static void main() {
        var solution = new ValidWordsForPuzzles();
        PrintUtils.printList(solution.findNumOfValidWords(new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"}, new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"}));
        PrintUtils.printList(solution.findNumOfValidWords(new String[]{"apple", "pleas", "please"}, new String[]{"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"}));
    }

}
