package org.lyndon.algo;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class CountOfAtoms {

    private static String countOfAtoms(String formula) {
        int len = formula.length();
        Stack<Pair<String, Integer>> stack = new Stack<>();
        for (int i = 0; i < formula.length();) {
            if (formula.charAt(i) == '(') {
                stack.push(Pair.of("(", 0));
                i++;
            } else if (formula.charAt(i) == ')') {
                i++;
                StringBuilder count = new StringBuilder("0");
                while (i < len && Character.isDigit(formula.charAt(i))) {
                    count.append(formula.charAt(i));
                    i++;
                }
                int cnt = Integer.parseInt(count.toString());
                if (cnt == 0) {
                    cnt = 1;
                }
                Stack<Pair<String, Integer>> tmp = new Stack<>();
                while (!stack.peek().getLeft().equals("(")) {
                    tmp.push(stack.pop());
                }
                stack.pop();
                while(!tmp.isEmpty()) {
                    Pair<String, Integer> pair = tmp.pop();
                    stack.push(Pair.of(pair.getLeft(), pair.getRight() * cnt));
                }
            } else if (Character.isUpperCase(formula.charAt(i))) {
                StringBuilder element = new StringBuilder("" + formula.charAt(i++));
                while (i < len && Character.isLowerCase(formula.charAt(i))) {
                    element.append(formula.charAt(i));
                    i++;
                }
                StringBuilder count = new StringBuilder("0");
                while (i < len && Character.isDigit(formula.charAt(i))) {
                    count.append(formula.charAt(i));
                    i++;
                }
                int cnt = Integer.parseInt(count.toString());
                if (cnt == 0) {
                    cnt = 1;
                }
                stack.push(Pair.of(element.toString(), cnt));
            } else {
                i++;
            }
        }
        Map<String, Integer> map = new TreeMap<>();
        while (!stack.isEmpty()) {
            Pair<String, Integer> pair = stack.pop();
            map.put(pair.getLeft(), map.getOrDefault(pair.getLeft(), 0) + pair.getRight());
        }
        StringBuilder result = new StringBuilder();
        map.forEach((key, value) -> {
            result.append(key);
            if (value > 1) {
                result.append(value);
            }
        });
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(countOfAtoms("H2O"));
        System.out.println(countOfAtoms("Mg(OH)2"));
        System.out.println(countOfAtoms("K4(ON(SO3)2)2"));
    }

}
