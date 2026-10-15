package org.lyndon.algo.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * LC1081
 * Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 * <p>
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 */
public class SmallestSubsequenceOfDistinctChars {

    public String smallestSubsequence(String s) {
        Stack<Character> stack = new Stack<>();
        int[] lastIndex = new int[26];
        boolean[] inStack = new boolean[26];
        Arrays.fill(lastIndex, -1);
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (inStack[c - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && c < stack.peek() && lastIndex[stack.peek() - 'a'] > i) {
                inStack[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            inStack[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        var solution = new SmallestSubsequenceOfDistinctChars();
        System.out.println(solution.smallestSubsequence("bcabc"));
        System.out.println(solution.smallestSubsequence("cbacdcbc"));
    }

}
