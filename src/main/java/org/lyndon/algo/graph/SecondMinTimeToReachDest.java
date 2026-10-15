package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC2045
 */
public class SecondMinTimeToReachDest {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] t1 = new int[n + 1];
        Arrays.fill(t1, -1);
        int[] t2 = new int[n + 1];
        Arrays.fill(t2, -1);
        t1[1] = 0;
        // [node, arrival_time]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int u = curr[0];
            int t = curr[1];
            int nt = next(t, time, change);

            for (int v : graph[u]) {
                if (t1[v] == -1) {
                    t1[v] = nt;
                    queue.offer(new int[]{v, nt});
                } else if (t2[v] == -1 && t1[v] < nt) {
                    if (v == n) {
                        return nt;
                    }
                    t2[v] = nt;
                    queue.offer(new int[]{v, nt});
                }
            }
        }
        return -1;
    }

    private int next(int t, int time, int change) {
        if ((t / change % 2) == 1) {
            return (t / change + 1) * change + time;
        }
        return t + time;
    }

}
