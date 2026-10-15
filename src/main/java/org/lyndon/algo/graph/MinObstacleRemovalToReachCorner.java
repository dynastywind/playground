package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LC2290
 */
public class MinObstacleRemovalToReachCorner {

    public int minimumObstacles(int[][] grid) {
        final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;
        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerFirst(0);
        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();
            int x = cur / n;
            int y = cur % n;
            if (x == m - 1 && y == n - 1) {
                return dist[cur];
            }

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                int key = nx * n + ny;
                int nd = dist[cur] + grid[nx][ny];
                if (nd < dist[key]) {
                    dist[key] = nd;
                    if (grid[nx][ny] == 0) {
                        dq.offerFirst(key);
                    } else {
                        dq.offerLast(key);
                    }
                }
            }
        }
        return dist[m * n - 1];
    }

}
