package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC3620
 */
public class NetworkRecoveryPath {

    private int n;
    private List<int[]>[] graph;
    private List<Integer> topo;
    private long k;

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.n = online.length;
        this.k = k;

        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[n];
        int maxCost = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int c = edge[2];
            if (!online[u] || !online[v] || c > k) {
                continue;
            }
            graph[u].add(new int[]{v, c});
            indegree[v]++;
            maxCost = Math.max(maxCost, c);
        }

        topo = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topo.add(u);
            for (int[] e : graph[u]) {
                int v = e[0];
                if (--indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        int lo = 0;
        int hi = maxCost;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int x) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        for (int u : topo) {
            if (dist[u] == Long.MAX_VALUE) {
                continue;
            }
            for (int[] e : graph[u]) {
                if (e[1] < x) {
                    continue;
                }
                long nd = dist[u] + e[1];
                if (nd < dist[e[0]]) {
                    dist[e[0]] = nd;
                }
            }
        }
        return dist[n - 1] <= k;
    }

}
