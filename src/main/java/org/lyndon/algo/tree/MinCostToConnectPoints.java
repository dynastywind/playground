package org.lyndon.algo.tree;

import java.util.Arrays;

/**
 * LC1584
 */
public class MinCostToConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] inMST = new boolean[n];

        int cost = 0;
        for (int i = 0; i < n; i++) {
            int cur = -1;
            for (int j = 0; j < n; j++) {
                if (inMST[j]) {
                    continue;
                }
                if (cur == -1 || dist[j] < dist[cur]) {
                    cur = j;
                }
            }
            inMST[cur] = true;
            cost += dist[cur];
            for (int j = 0; j < n; j++) {
                if (inMST[j]) {
                    continue;
                }
                int w = Math.abs(points[cur][0] - points[j][0]) + Math.abs(points[cur][1] - points[j][1]);
                if (w < dist[j]) {
                    dist[j] = w;
                }
            }
        }
        return cost;
    }

}
