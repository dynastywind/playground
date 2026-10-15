package org.lyndon.algo.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * LC636
 */
public class ExclusiveTimeOfFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        int prev = 0;
        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            boolean start = parts[1].equals("start");
            int time = Integer.parseInt(parts[2]);
            if (start) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += time - prev;
                }
                stack.push(id);
                prev = time;
            } else {
                res[stack.pop()] += time - prev + 1;
                prev = time + 1;
            }
        }
        return res;
    }

}
