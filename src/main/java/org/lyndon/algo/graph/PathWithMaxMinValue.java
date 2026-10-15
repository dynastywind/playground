package org.lyndon.algo.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LC 1102
 * Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and ending at (m - 1, n - 1) moving in the 4 cardinal directions.
 * <p>
 * The score of a path is the minimum value in that path.
 * <p>
 * For example, the score of the path 8 → 4 → 5 → 9 is 4.
 */
public class PathWithMaxMinValue {

    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // [w, x, y]
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        pq.offer(new int[]{grid[0][0], 0, 0});
        while (!pq.isEmpty()) {
            int[] item = pq.poll();
            int w = item[0];
            int x = item[1];
            int y = item[2];
            if (x == m - 1 && y == n - 1) {
                return w;
            }
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                pq.offer(new int[]{Math.min(w, grid[nx][ny]), nx, ny});
            }
        }
        return -1;
    }

}
