package org.lyndon.algo.graph;

/**
 * LC64
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 */
public class MinPathSum {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        dist[0][0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dist[0][j] = dist[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            dist[i][0] = dist[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dist[i][j] = Math.min(dist[i - 1][j], dist[i][j - 1]) + grid[i][j];
            }
        }
        return dist[m - 1][n - 1];
    }

}
