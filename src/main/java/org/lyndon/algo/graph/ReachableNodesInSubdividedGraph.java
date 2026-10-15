package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LC882
 */
public class ReachableNodesInSubdividedGraph {

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2] + 1;
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];
            if (d > dist[u]) {
                continue;
            }
            if (d > maxMoves) {
                break;
            }
            for (int[] nb : graph[u]) {
                int v = nb[0];
                int nd = d + nb[1];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new int[]{v, nd});
                }
            }
        }

        int ans = 0;
        for (int d : dist) {
            if (d <= maxMoves) {
                ans++;
            }
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cnt = edge[2];
            int a = dist[u] <= maxMoves ? maxMoves - dist[u] : 0;
            int b = dist[v] <= maxMoves ? maxMoves - dist[v] : 0;
            ans += Math.min(cnt, a + b);
        }
        return ans;
    }

}
