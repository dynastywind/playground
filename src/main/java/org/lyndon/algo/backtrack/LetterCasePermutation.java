package org.lyndon.algo.backtrack;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * LC784
 */
public class LetterCasePermutation {

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String s, int i, StringBuilder sb, List<String> result) {
        if (i == s.length()) {
            result.add(sb.toString());
            return;
        }
        char c = s.charAt(i);
        if (Character.isAlphabetic(c)) {
            sb.append(Character.toLowerCase(c));
            backtrack(s, i + 1, sb, result);
            sb.setCharAt(sb.length() - 1, Character.toUpperCase(c));
            backtrack(s, i + 1, sb, result);
            sb.setLength(sb.length() - 1);
        } else {
            sb.append(c);
            backtrack(s, i + 1, sb, result);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        var casePermutation = new LetterCasePermutation();
        PrintUtils.printList(casePermutation.letterCasePermutation("a1b2"));
        PrintUtils.printList(casePermutation.letterCasePermutation("3z4"));
    }

}
