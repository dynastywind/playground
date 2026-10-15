package org.lyndon.algo.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC505
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * <p>
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop at the destination. If the ball cannot stop at destination, return -1.
 * <p>
 * The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
 * <p>
 * You may assume that the borders of the maze are all walls.
 */
public class MazeII {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        // [x, y, d]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[]{start[0], start[1], 0});
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            if (d > dist[x][y]) {
                continue;
            }
            if (x == destination[0] && y == destination[1]) {
                return d;
            }
            for (int[] dir : dirs) {
                int nx = x;
                int ny = y;
                int steps = 0;
                while (nx + dir[0] >= 0 && nx + dir[0] < m && ny + dir[1] >= 0 && ny + dir[1] < n && maze[nx + dir[0]][ny + dir[1]] == 0) {
                    nx += dir[0];
                    ny += dir[1];
                    steps++;
                }
                if (dist[nx][ny] > d + steps) {
                    dist[nx][ny] = d + steps;
                    pq.offer(new int[]{nx, ny, dist[nx][ny]});
                }
            }
        }
        return -1;
    }

}
