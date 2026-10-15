package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LC317
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 * <p>
 * each 0 marks an empty land that you can pass by freely,
 * each 1 marks a building that you cannot pass through, and
 * each 2 marks an obstacle that you cannot pass through.
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.
 * <p>
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
 * <p>
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 */
public class ShortestDistanceFromAllBuildings {

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[][] dist = new int[m][n];
        int buildings = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{i, j});
                int steps = 0;
                while (!queue.isEmpty()) {
                    steps++;
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        int[] cur = queue.poll();
                        for (int[] dir : dirs) {
                            int nx = cur[0] + dir[0];
                            int ny = cur[1] + dir[1];
                            if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] != buildings) {
                                continue;
                            }
                            grid[nx][ny]--;
                            dist[nx][ny] += steps;
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
                buildings--;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == buildings) {
                    ans = Math.min(ans, dist[i][j]);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
