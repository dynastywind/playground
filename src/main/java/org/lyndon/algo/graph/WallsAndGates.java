package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LC286
 * You are given an m x n grid rooms initialized with these three possible values.
 * <p>
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF
 */
public class WallsAndGates {

    private static final int INF = 2147483647;

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || rooms[nx][ny] != INF) {
                    continue;
                }
                rooms[nx][ny] = rooms[x][y] + 1;
                queue.offer(new int[]{nx, ny});
            }
        }
    }

}
