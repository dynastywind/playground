package org.lyndon.algo.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC1190
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {

    private int i;

    public String reverseParenthesesRec(String s) {
        this.i = 0;
        return reverse(s);
    }

    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(new StringBuilder());
            } else if (c == ')') {
                StringBuilder b = stack.pop();
                String str = b.reverse().toString();
                if (!stack.isEmpty()) {
                    stack.peek().append(str);
                } else {
                    sb.append(str);
                }
            } else {
                if (!stack.isEmpty()) {
                    stack.peek().append(c);
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            char c = s.charAt(i++);
            switch (c) {
                case ')':
                    return sb.reverse().toString();
                case '(':
                    sb.append(reverse(s));
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    static void main() {
        var solution = new ReverseSubstringsBetweenEachPairOfParentheses();
        System.out.println(solution.reverseParentheses("(abcd)"));
        System.out.println(solution.reverseParentheses("(u(love)i)"));
        System.out.println(solution.reverseParentheses("(ed(et(oc))el)"));
    }

}
