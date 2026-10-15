package org.lyndon.algo.graph;

/**
 * LC1289
 * Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
 * <p>
 * A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.
 */
public class MinFallingPathII {

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int min = Integer.MAX_VALUE;
        int secMin = Integer.MAX_VALUE;
        int minCol = -1;
        int[] dist = grid[0].clone();
        for (int i = 0; i < n; i++) {
            if (dist[i] < min) {
                secMin = min;
                min = dist[i];
                minCol = i;
            } else if (dist[i] < secMin) {
                secMin = dist[i];
            }
        }
        for (int i = 1; i < n; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecMin = Integer.MAX_VALUE;
            int curMinCol = -1;
            for (int j = 0; j < n; j++) {
                if (j == minCol) {
                    dist[j] = grid[i][j] + secMin;
                } else {
                    dist[j] = grid[i][j] + min;
                }
                if (dist[j] < curMin) {
                    curSecMin = curMin;
                    curMin = dist[j];
                    curMinCol = j;
                } else if (dist[j] < curSecMin) {
                    curSecMin = dist[j];
                }
            }
            min = curMin;
            secMin = curSecMin;
            minCol = curMinCol;
        }
        return min;
    }

    public static void main(String[] args) {
        var solution = new MinFallingPathII();
        int[][] grid = new int[][]{
                {2, 2, 1, 2, 2}, {2, 2, 1, 2, 2}, {2, 2, 1, 2, 2}, {2, 2, 1, 2, 2}, {2, 2, 1, 2, 2}
        };
        System.out.println(solution.minFallingPathSum(grid));
    }

}
