package org.lyndon.algo.stack;

import java.util.ArrayDeque;

/**
 * LC921
 */
public class MinAddMakeParenthesesValid {

    public int minAddToMakeValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    ans++;
                }
            }
        }
        return ans + stack.size();
    }

}
