package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LC1928
 * There is a country of n cities numbered from 0 to n - 1 where all the cities are connected by bi-directional roads. The roads are represented as a 2D integer array edges where edges[i] = [xi, yi, timei] denotes a road between cities xi and yi that takes timei minutes to travel. There may be multiple roads of differing travel times connecting the same two cities, but no road connects a city to itself.
 * <p>
 * Each time you pass through a city, you must pay a passing fee. This is represented as a 0-indexed integer array passingFees of length n where passingFees[j] is the amount of dollars you must pay when you pass through city j.
 * <p>
 * In the beginning, you are at city 0 and want to reach city n - 1 in maxTime minutes or less. The cost of your journey is the summation of passing fees for each city that you passed through at some moment of your journey (including the source and destination cities).
 * <p>
 * Given maxTime, edges, and passingFees, return the minimum cost to complete your journey, or -1 if you cannot complete it within maxTime minutes.
 */
public class MinCostToReachDestInTime {

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        // [{{v, time}}]
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];
            graph[u].add(new int[]{v, time});
            graph[v].add(new int[]{u, time});
        }

        int[] time = new int[n];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[0] = 0;
        // [cost, time, u]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{passingFees[0], 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int c = cur[0];
            int t = cur[1];
            int u = cur[2];
            if (t >= time[u]) {
                continue;
            }
            time[u] = t;
            if (u == n - 1) {
                return c;
            }
            for (int[] nb : graph[u]) {
                int nv = nb[0];
                int nt = nb[1] + t;
                if (nt > maxTime || nt >= time[nv]) {
                    continue;
                }
                pq.offer(new int[]{passingFees[nv] + c, nt, nv});
            }
        }
        return -1;
    }

}
