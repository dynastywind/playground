package org.lyndon.algo.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LC678
 */
public class ValidParenthesisString {

    public boolean checkValidString(String s) {
        Deque<Integer> left = new LinkedList<>();
        Deque<Integer> star = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left.push(i);
            } else if (c == '*') {
                star.push(i);
            } else {
                if (!left.isEmpty()) {
                    left.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else {
                    return false;
                }
            }
        }
        while (!left.isEmpty() && !star.isEmpty()) {
            if (left.pop() > star.pop()) {
                return false;
            }
        }
        return left.isEmpty();
    }

}
