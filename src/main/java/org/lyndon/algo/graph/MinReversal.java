package org.lyndon.algo.graph;

import java.util.*;

public class MinReversal {

    public int minReversals(int n, int[][] edges, int src, int dst) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(new int[]{v, 0});
            graph[v].add(new int[]{u, 1});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(src);
        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            if (u == dst) {
                return dist[u];
            }
            for (int[] e : graph[u]) {
                int v = e[0];
                int w = e[1];
                int nd = dist[u] + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    if (w == 0) {
                        dq.offerFirst(v);
                    } else {
                        dq.offerLast(v);
                    }
                }
            }
        }
        return -1;
    }

}
