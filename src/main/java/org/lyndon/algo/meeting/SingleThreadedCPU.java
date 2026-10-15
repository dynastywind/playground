package org.lyndon.algo.meeting;

import org.lyndon.utils.PrintUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC1834
 */
public class SingleThreadedCPU {

    public int[] getOrder(int[][] tasks) {
        // [processing_time, index]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        int n = tasks.length;
        // [enqueue_time, process_time, i]
        int[][] ts = new int[n][3];
        for (int i = 0; i < n; i++) {
            int[] task = tasks[i];
            ts[i] = new int[]{task[0], task[1], i};
        }
        Arrays.sort(ts, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        int[] ans = new int[n];
        int j = 0;
        int i = 0;
        long time = 0;
        while (j < n) {
            while (i < n && ts[i][0] <= time) {
                pq.offer(new int[]{ts[i][1], ts[i][2]});
                i++;
            }
            if (pq.isEmpty()) {
                time = ts[i][0];
                continue;
            }
            int[] t = pq.poll();
            ans[j++] = t[1];
            time += t[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new SingleThreadedCPU();
        PrintUtils.printArray(solution.getOrder(new int[][]{{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}}));
    }

}
