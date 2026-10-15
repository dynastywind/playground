package org.lyndon.algo.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC542
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two cells sharing a common edge is 1.
 */
public class MatrixZeroOne {

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;
                }
            }
        }
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || seen[nx][ny]) {
                    continue;
                }
                mat[nx][ny] = mat[x][y] + 1;
                queue.offer(new int[]{nx, ny});
                seen[nx][ny] = true;
            }
        }
        return mat;
    }

}
