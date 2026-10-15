package org.lyndon.algo.stack;

import java.util.ArrayDeque;

/**
 * LC32
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int ans = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new LongestValidParentheses();
        System.out.println(solution.longestValidParentheses("(()"));
        System.out.println(solution.longestValidParentheses(")()())"));
        System.out.println(solution.longestValidParentheses(")()())()()()("));
        System.out.println(solution.longestValidParentheses("()(()"));
    }

}
