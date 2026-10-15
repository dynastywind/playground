package org.lyndon.algo.meeting;

import org.lyndon.utils.PrintUtils;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LC1882
 */
public class ProcessTasksUsingServers {

    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<Integer> availableServers = new PriorityQueue<>((a, b) -> {
            if (servers[a] != servers[b]) {
                return Integer.compare(servers[a], servers[b]);
            }
            return Integer.compare(a, b);
        });
        for (int i = 0; i < servers.length; i++) {
            availableServers.add(i);
        }
        // [next_time, index]
        PriorityQueue<long[]> nextAvailableServers = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        Queue<Integer> taskQueue = new LinkedList<>();
        int n = tasks.length;
        int[] ans = new int[n];
        int i = 0;
        long time = 0;
        while (i < n || !taskQueue.isEmpty()) {
            while (i < n && i <= time) {
                taskQueue.offer(i++);
            }
            while (!nextAvailableServers.isEmpty() && nextAvailableServers.peek()[0] <= time) {
                long[] server = nextAvailableServers.poll();
                availableServers.offer((int) server[1]);
            }
            while (!availableServers.isEmpty() && !taskQueue.isEmpty()) {
                int server = availableServers.poll();
                int task = taskQueue.poll();
                ans[task] = server;
                nextAvailableServers.offer(new long[]{time + tasks[task], server});
            }
            if (!taskQueue.isEmpty()) {
                time = nextAvailableServers.peek()[0];
            } else if (i < n) {
                time = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new ProcessTasksUsingServers();
        PrintUtils.printArray(solution.assignTasks(new int[]{3, 3, 2}, new int[]{1, 2, 3, 2, 1, 2}));
        PrintUtils.printArray(solution.assignTasks(new int[]{5, 1, 4, 3, 2}, new int[]{2, 1, 2, 4, 5, 2, 1}));
    }

}
