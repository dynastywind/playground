package org.lyndon.algo.stack;

import java.util.Stack;

/**
 * LC84
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram
 */
public class LargestRectInHistogram {

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                ans = Math.max(ans, height * width);
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new LargestRectInHistogram();
        System.out.println(solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

}
