package org.lyndon.algo.stack;

import java.util.ArrayDeque;

/**
 * LC1944
 */
public class NumOfVisiblePeopleInAQueue {

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int cnt = 0;
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.poll();
                cnt++;
            }
            ans[i] = cnt + (stack.isEmpty() ? 0 : 1);
            stack.push(heights[i]);
        }
        return ans;
    }

}
