package org.lyndon.algo.stack;

import org.lyndon.utils.PrintUtils;

import java.util.Stack;

/**
 * LC739
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * Example 1:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * <p>
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * <p>
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                ans[i] = stack.peek() - i;
            } else {
                ans[i] = 0;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        var dailyTemperatures = new DailyTemperatures();
        PrintUtils.printArray(dailyTemperatures.dailyTemperatures(new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70}));
    }

}
