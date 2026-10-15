package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC2247
 * A series of highways connect n cities numbered from 0 to n - 1. You are given a 2D integer array highways where highways[i] = [city1i, city2i, tolli] indicates that there is a highway that connects city1i and city2i, allowing a car to go from city1i to city2i and vice versa for a cost of tolli.
 * <p>
 * You are also given an integer k. You are going on a trip that crosses exactly k highways. You may start at any city, but you may only visit each city at most once during your trip.
 * <p>
 * Return the maximum cost of your trip. If there is no trip that meets the requirements, return -1
 */
public class MaxCostOfTripWIthKHighways {

    private int ans = -1;

    public int maximumCost(int n, int[][] highways, int k) {
        if (k >= n) {
            return -1;
        }

        List<int[]>[] graph = buildGraph(n, highways);
//        boolean[] visited = new boolean[n];
//        for (int i = 0; i < n; i++) {
//            dfs(graph, i, 0, k, 0, visited);
//        }
//        return ans;
        return bitmask(graph, n, highways, k);
    }

    private List<int[]>[] buildGraph(int n, int[][] highways) {
        List<int[]>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] highway : highways) {
            graph[highway[0]].add(new int[]{highway[1], highway[2]});
            graph[highway[1]].add(new int[]{highway[0], highway[2]});
        }
        return graph;
    }

    private int bitmask(List<int[]>[] graph, int n, int[][] highways, int k) {
        final int FULL = 1 << n;
        int[][] dp = new int[FULL][n];
        for (int[] d : dp) {
            Arrays.fill(d, -1); // unreachable
        }
        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 0;
        }
        int ans = -1;
        for (int mask = 1; mask < FULL; mask++) {
            int cnt = Integer.bitCount(mask);
            if (cnt > k + 1) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (dp[mask][i] < 0) {
                    continue;
                }
                if (cnt == k + 1) {
                    ans = Math.max(ans, dp[mask][i]);
                    continue;
                }
                for (int[] e : graph[i]) {
                    int v = e[0];
                    // if already in the set
                    if ((mask >> v & 1) != 0) {
                        continue;
                    }
                    int w = e[1];
                    int next = mask | (1 << v);
                    dp[next][v] = Math.max(dp[next][v], dp[mask][i] + w);
                }
            }
        }
        return ans;
    }

    private void dfs(List<int[]>[] graph, int u, int highways, int k, int cost, boolean[] visited) {
        if (highways == k) {
            ans = Math.max(ans, cost);
            return;
        }
        if (visited[u]) {
            return;
        }
        visited[u] = true;
        for (int[] e : graph[u]) {
            if (visited[e[0]]) {
                continue;
            }
            dfs(graph, e[0], highways + 1, k, cost + e[1], visited);
        }
        visited[u] = false;
    }

    public static void main(String[] args) {
        MaxCostOfTripWIthKHighways solution = new MaxCostOfTripWIthKHighways();
        System.out.println(solution.maximumCost(4, new int[][]{{0, 1, 3}, {2, 3, 2}}, 2));
    }

}
