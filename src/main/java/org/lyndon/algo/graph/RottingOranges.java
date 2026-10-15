package org.lyndon.algo.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC994
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 */
public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0) {
            return 0;
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int time = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] != 1) {
                        continue;
                    }
                    grid[nx][ny] = 2;
                    fresh--;
                    queue.offer(new int[]{nx, ny});
                }
            }
            time++;
        }
        return fresh == 0 ? time : -1;
    }

}
