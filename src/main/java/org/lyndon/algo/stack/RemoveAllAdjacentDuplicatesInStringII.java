package org.lyndon.algo.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC1209
 */
public class RemoveAllAdjacentDuplicatesInStringII {

    private record Pair<K, V>(K key, V value) {
    }

    public String removeDuplicates(String s, int k) {
        Deque<Pair<Character, Integer>> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || stack.peek().key() != c) {
                stack.push(new Pair<>(c, 1));
            } else {
                Pair<Character, Integer> pair = stack.pop();
                if (pair.value() + 1 != k) {
                    stack.push(new Pair<>(c, pair.value() + 1));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair<Character, Integer> pair = stack.pop();
            sb.repeat(pair.key(), pair.value());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        var solution = new RemoveAllAdjacentDuplicatesInStringII();
        System.out.println(solution.removeDuplicates("abcd", 2));
        System.out.println(solution.removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(solution.removeDuplicates("pbbcggttciiippooaais", 2));
    }

}
