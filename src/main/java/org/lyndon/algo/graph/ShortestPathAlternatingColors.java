package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC1129
 */
public class ShortestPathAlternatingColors {

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] redGraph = new List[n];
        List<Integer>[] blueGraph = new List[n];
        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }
        for (int[] e : redEdges) {
            redGraph[e[0]].add(e[1]);
        }
        for (int[] e : blueEdges) {
            blueGraph[e[0]].add(e[1]);
        }

        int[][] dist = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[0][0] = 0;
        dist[0][1] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int u = cur[0];
            int c = cur[1];
            int nc = 1 - c;
            List<Integer>[] graph = nc == 0 ? redGraph : blueGraph;
            for (int v : graph[u]) {
                if (dist[v][nc] == -1) {
                    dist[v][nc] = dist[u][c] + 1;
                    queue.offer(new int[]{v, nc});
                }
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int red = dist[i][0];
            int blue = dist[i][1];
            if (red == -1 && blue == -1) {
                ans[i] = -1;
            } else if (red == -1) {
                ans[i] = blue;
            } else if (blue == -1) {
                ans[i] = red;
            } else {
                ans[i] = Math.min(red, blue);
            }
        }
        return ans;

    }

}
