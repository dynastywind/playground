package org.lyndon.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * LC17
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */
public class LetterCombinationOfPhoneNumber {

    public List<String> letterCombinations(String digits) {
        String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), dict, res);
        return res;
    }

    private void backtrack(String digits, int i, StringBuilder sb, String[] dict, List<String> res) {
        if (i == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letters = dict[digits.charAt(i) - '0'];
        for (int j = 0; j < letters.length(); j++) {
            sb.append(letters.charAt(j));
            backtrack(digits, i + 1, sb, dict, res);
            sb.setLength(sb.length() - 1);
        }
    }

}
