package org.lyndon.algo.graph;

/**
 * PracHub Count Overlapping Rectangle Updates on a Grid
 */
public class CountOverlappingRectangleUpdatesOnAGrid {

    public int[][] range_add_counts(int n, int[][] rectangles) {
        int[][] diff = new int[n + 1][n + 1];
        for (int[] rect : rectangles) {
            int r1 = rect[0];
            int c1 = rect[1];
            int r2 = rect[2];
            int c2 = rect[3];
            diff[r1][c1]++;
            diff[r1][c2 + 1]--;
            diff[r2 + 1][c1]--;
            diff[r2 + 1][c2 + 1]++;
        }
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int v = diff[i][j];
                if (i > 0) {
                    v += ans[i - 1][j];
                }
                if (j > 0) {
                    v += ans[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    v -= diff[i - 1][j - 1];
                }
                ans[i][j] = v;
            }
        }
        return ans;
    }

}
