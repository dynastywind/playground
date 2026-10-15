package org.lyndon.algo.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LC1091
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 * <p>
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * <p>
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 */
public class ShortestPathInBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1) {
            return -1;
        }

        int[][] dist = new int[n][n];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE / 2);
        }
        dist[0][0] = 1;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {1, 1}, {1, -1}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}};
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if (x == n - 1 && y == n - 1) {
                return dist[x][y];
            }
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || grid[nx][ny] == 1 || visited[nx][ny]) {
                    continue;
                }
                if (dist[nx][ny] <= dist[x][y] + 1) {
                    continue;
                }
                dist[nx][ny] = dist[x][y] + 1;
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }
        return -1;
    }

    /*
        0,1,1,0,0,0
        0,1,0,1,1,0
        0,1,1,0,1,0
        0,0,0,1,1,0
        1,1,1,1,1,0
        1,1,1,1,1,0
     */
    public static void main(String[] args) {
        var solution = new ShortestPathInBinaryMatrix();
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}));
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{0, 0, 0}, {1, 1, 1}, {1, 1, 0}}));
        System.out.println(solution.shortestPathBinaryMatrix(new int[][]{{1, 0, 0}, {1, 1, 1}, {1, 1, 0}}));
    }

}
