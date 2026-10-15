package org.lyndon.design;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * LC224
 */
public class BasicCalculator {

    public int calculate(String s) {
        s = s.replace(" ", "");
        ArrayDeque<Character> stack = new ArrayDeque<>();
        List<Object> expression = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '-') {
                if (expression.isEmpty() || s.charAt(i - 1) == '(') {
                    expression.addLast(0);
                }
            }
            if (!Character.isDigit(c)) {
                if (c == ')') {
                    while (stack.peek() != '(') {
                        expression.add(stack.pop());
                    }
                    stack.pop();
                } else if (c == '(') {
                    stack.push(c);
                } else {
                    if (!stack.isEmpty() && stack.peek() != '(') {
                        expression.add(stack.pop());
                    }
                    stack.push(c);
                }
                i++;
            } else {
                int res = c - '0';
                int j = i + 1;
                while (j < s.length()) {
                    char ch = s.charAt(j);
                    if (Character.isDigit(ch)) {
                        res = res * 10 + (ch - '0');
                        j++;
                    } else {
                        break;
                    }
                }
                expression.add(res);
                i = j;
            }
        }
        while (!stack.isEmpty()) {
            expression.add(stack.pop());
        }
        ArrayDeque<Integer> es = new ArrayDeque<>();
        for (Object item : expression) {
            if (item instanceof Integer) {
                es.push((Integer) item);
            } else if (item instanceof Character) {
                int b = es.pop();
                int a = es.pop();
                if ((Character) item == '+') {
                    es.push(a + b);
                } else if ((Character) item == '-') {
                    es.push(a - b);
                }
            }
        }
        return es.pop();
    }

    public static void main(String[] args) {
        var solution = new BasicCalculator();
        System.out.println(solution.calculate("-1+(-2)"));
        System.out.println(solution.calculate("1+1"));
    }

}
