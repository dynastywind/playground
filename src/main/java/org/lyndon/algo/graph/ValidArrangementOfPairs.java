package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC2097
 * You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.
 * <p>
 * Return any valid arrangement of pairs.
 * <p>
 * Note: The inputs will be generated such that there exists a valid arrangement of pairs.
 * <p>
 * Example 1:
 * <p>
 * Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
 * Output: [[11,9],[9,4],[4,5],[5,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 9 == 9 = start1
 * end1 = 4 == 4 = start2
 * end2 = 5 == 5 = start3
 * Example 2:
 * <p>
 * Input: pairs = [[1,3],[3,2],[2,1]]
 * Output: [[1,3],[3,2],[2,1]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 3 == 3 = start1
 * end1 = 2 == 2 = start2
 * The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
 * Example 3:
 * <p>
 * Input: pairs = [[1,2],[1,3],[2,1]]
 * Output: [[1,2],[2,1],[1,3]]
 * Explanation:
 * This is a valid arrangement since endi-1 always equals starti.
 * end0 = 2 == 2 = start1
 * end1 = 1 == 1 = start2
 */
public class ValidArrangementOfPairs {

    public int[][] validArrangement(int[][] pairs) {
        int n = pairs.length;
        Map<Integer, Deque<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> degree = new HashMap<>();
        for (int[] pair : pairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayDeque<>()).add(pair[1]);
            degree.merge(pair[0], 1, Integer::sum);
            degree.merge(pair[1], -1, Integer::sum);
        }

        int start = pairs[0][0];
        for (Map.Entry<Integer, Integer> entry : degree.entrySet()) {
            if (entry.getValue() == 1) {
                start = entry.getKey();
                break;
            }
        }

        int[] path = new int[n + 1];
        int index = n;
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int u = stack.peek();
            Deque<Integer> adj = graph.get(u);
            if (adj == null || adj.isEmpty()) {
                path[index--] = u;
                stack.pop();
            } else {
                stack.push(adj.pop());
            }
        }

        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) {
            ans[i][0] = path[i];
            ans[i][1] = path[i + 1];
        }
        return ans;
    }

}
