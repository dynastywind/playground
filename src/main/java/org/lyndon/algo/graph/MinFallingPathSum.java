package org.lyndon.algo.graph;

/**
 * LC931
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 * <p>
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 */
public class MinFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {
        int[] dist = matrix[0].clone();
        int n = matrix.length;
        for (int i = 1; i < n; i++) {
            int[] cur = new int[n];
            for (int j = 0; j < n; j++) {
                int best = dist[j];
                if (j > 0) {
                    best = Math.min(best, dist[j - 1]);
                }
                if (j < n - 1) {
                    best = Math.min(best, dist[j + 1]);
                }
                cur[j] = best + matrix[i][j];
            }
            dist = cur;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < dist.length; i++) {
            ans = Math.min(ans, dist[i]);
        }
        return ans;
    }

}
