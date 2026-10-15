package org.lyndon.algo.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC2289
 */
public class StepsToMakeArrayNonDecreasing {

    public int totalSteps(int[] nums) {
        Deque<int[]> stack = new ArrayDeque<>();
        int ans = 0;
        for (int num : nums) {
            int cur = 0;
            while (!stack.isEmpty() && stack.peek()[0] <= num) {
                cur = Math.max(cur, stack.pop()[1]);
            }
            cur = stack.isEmpty() ? 0 : cur + 1;
            ans = Math.max(ans, cur);
            stack.push(new int[]{num, cur});
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new StepsToMakeArrayNonDecreasing();
        System.out.println(solution.totalSteps(new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11}));
        System.out.println(solution.totalSteps(new int[]{4, 5, 7, 7, 13}));
        System.out.println(solution.totalSteps(new int[]{4, 4}));
        System.out.println(solution.totalSteps(new int[]{5, 4, 3, 2, 1}));
    }

}
