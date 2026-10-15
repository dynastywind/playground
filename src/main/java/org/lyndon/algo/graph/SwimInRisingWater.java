package org.lyndon.algo.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LC778
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 * <p>
 * It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.
 * <p>
 * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 * <p>
 * Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0)
 */
public class SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        int[][] dir = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        pq.add(new int[]{0, 0, grid[0][0]});
        while (!pq.isEmpty()) {
            int[] pos = pq.poll();
            int x = pos[0];
            int y = pos[1];
            int w = pos[2];
            if (w > dist[x][y]) {
                continue;
            }
            if (x == n - 1 && y == n - 1) {
                return w;
            }
            for (int[] d : dir) {
                int nx = d[0] + x;
                int ny = d[1] + y;
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }
                int nw = Math.max(w, grid[nx][ny]);
                if (nw < dist[nx][ny]) {
                    dist[nx][ny] = nw;
                    pq.add(new int[]{nx, ny, nw});
                }
            }
        }
        return -1;
    }

}
