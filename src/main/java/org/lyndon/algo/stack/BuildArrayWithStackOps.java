package org.lyndon.algo.stack;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * LC1441
 */
public class BuildArrayWithStackOps {

    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        int m = target.length;
        int[] stack = new int[m];
        int top = -1;
        int turn = 0;
        for (int t : target) {
            while (top < 0 || stack[top] != t) {
                stack[++top] = turn + 1;
                result.add("Push");
                if (stack[top] != t) {
                    top--;
                    result.add("Pop");
                }
                turn = (turn + 1) % n;
            }
        }
        return result;
    }

    static void main() {
        var solution = new BuildArrayWithStackOps();
        PrintUtils.printList(solution.buildArray(new int[]{1, 3}, 3));
        PrintUtils.printList(solution.buildArray(new int[]{1, 2, 3}, 3));
        PrintUtils.printList(solution.buildArray(new int[]{1, 2}, 4));
    }

}
