package org.lyndon.algo.graph.eulerian;

import java.util.Stack;

/**
 * LC753
 * There is a safe protected by a password. The password is a sequence of n digits where each digit can be in the range [0, k - 1].
 * <p>
 * The safe has a peculiar way of checking the password. When you enter in a sequence, it checks the most recent n digits that were entered each time you type a digit.
 * <p>
 * For example, the correct password is "345" and you enter in "012345":
 * After typing 0, the most recent 3 digits is "0", which is incorrect.
 * After typing 1, the most recent 3 digits is "01", which is incorrect.
 * After typing 2, the most recent 3 digits is "012", which is incorrect.
 * After typing 3, the most recent 3 digits is "123", which is incorrect.
 * After typing 4, the most recent 3 digits is "234", which is incorrect.
 * After typing 5, the most recent 3 digits is "345", which is correct and the safe unlocks.
 * Return any string of minimum length that will unlock the safe at some point of entering it.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1, k = 2
 * Output: "10"
 * Explanation: The password is a single digit, so enter each digit. "01" would also unlock the safe.
 * Example 2:
 * <p>
 * Input: n = 2, k = 2
 * Output: "01100"
 * Explanation: For each possible password:
 * - "00" is typed in starting from the 4th digit.
 * - "01" is typed in starting from the 1st digit.
 * - "10" is typed in starting from the 3rd digit.
 * - "11" is typed in starting from the 2nd digit.
 * Thus "01100" will unlock the safe. "10011", and "11001" would also unlock the safe.
 */
public class CrackingSafe {

    public String crackSafe(int n, int k) {
        int nodes = (int) Math.pow(k, n - 1);
        int[] iter = new int[nodes];

        // [curNode, sourceEdge]
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, -1});
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            int[] top = stack.peek();
            int u = top[0];
            // k edges per node
            if (iter[u] < k) {
                int d = iter[u]++;
                int edge = u * k + d;
                // get new node from edge: 110 -> 10
                stack.push(new int[]{edge % nodes, d});
            } else {
                stack.pop();
                if (top[1] >= 0) {
                    sb.append((char) (top[1] + '0'));
                }
            }
        }
        sb.reverse();
        sb.repeat('0', n - 1);
        return sb.toString();
    }

}
