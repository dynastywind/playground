package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LC743
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 * <p>
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph[u].add(new int[]{v, w});
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        // [node, weight]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];
            if (dist[u] < d) {
                continue;
            }
            for (int[] child : graph[u]) {
                int v = child[0];
                int w = child[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        int max = 0;
        for (int i = 1; i < dist.length; i++) {
            max = Math.max(max, dist[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }

}
