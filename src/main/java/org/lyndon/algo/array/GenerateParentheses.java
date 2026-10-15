package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * LC22
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(0, new char[2 * n], n, n, result);
        return result;
    }

    private void dfs(int i, char[] cs, int left, int right, List<String> result) {
        if (i == cs.length - 1) {
            cs[i] = ')';
            result.add(new String(cs));
            return;
        }
        if (left > 0) {
            cs[i] = '(';
            dfs(i + 1, cs, left - 1, right, result);
        }
        if (left < right) {
            cs[i] = ')';
            dfs(i + 1, cs, left, right - 1, result);
        }
    }

    public static void main(String[] args) {
        var solution = new GenerateParentheses();
        PrintUtils.printList(solution.generateParenthesis(4));
    }

}
