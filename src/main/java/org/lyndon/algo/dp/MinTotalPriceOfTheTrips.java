package org.lyndon.algo.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LC2646
 */
public class MinTotalPriceOfTheTrips {

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        int[] cnt = new int[n];
        for (int[] trip : trips) {
            countPath(trip[0], -1, trip[1], cnt, graph);
        }
        int[] dp = dfs(0, -1, price, cnt, graph);
        return Math.min(dp[0], dp[1]);
    }

    private boolean countPath(int u, int parent, int target, int[] cnt, List<Integer>[] graph) {
        if (u == target) {
            cnt[u]++;
            return true;
        }
        for (int v : graph[u]) {
            if (v != parent && countPath(v, u, target, cnt, graph)) {
                cnt[u]++;
                return true;
            }
        }
        return false;
    }

    private int[] dfs(int u, int parent, int[] price, int[] cnt, List<Integer>[] graph) {
        int original = price[u] * cnt[u];
        int halved = original / 2;
        for (int v : graph[u]) {
            if (v == parent) {
                continue;
            }
            int[] pair = dfs(v, u, price, cnt, graph);
            original += Math.min(pair[0], pair[1]);
            halved += pair[0];
        }
        return new int[]{original, halved};
    }

}
