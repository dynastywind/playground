package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LC1786
 */
public class NumberOfRestrictedPaths {

    public int countRestrictedPaths(int n, int[][] edges) {
        final int MOD = 1_000_000_007;
        List<int[]>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        long[] dist = dijkstra(n, graph, n);

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i + 1;
        }
        Arrays.sort(order, (a, b) -> Long.compare(dist[a], dist[b]));

        long[] dp = new long[n + 1];
        dp[n] = 1;
        for (int u : order) {
            if (u == n) {
                continue;
            }
            long sum = 0;
            for (int[] edge : graph[u]) {
                int v = edge[0];
                if (dist[v] < dist[u]) {
                    sum += dp[v];
                }
            }
            dp[u] = sum % MOD;
            if (u == 1) {
                break;
            }
        }
        return (int) dp[1];
    }

    private long[] dijkstra(int n, List<int[]>[] graph, int start) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        // {dist, node}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[]{0, start});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (d > dist[u]) {
                continue;
            }
            for (int[] edge : graph[u]) {
                int v = edge[0];
                long nd = d + edge[1];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{nd, v});
                }
            }
        }
        return dist;
    }

}
