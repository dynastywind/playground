package org.lyndon.algo.array;

/**
 * LC2132
 */
public class StampingTheGrid {

    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] s = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s[i + 1][j + 1] = s[i][j + 1] + s[i + 1][j] - s[i][j] + grid[i][j];
            }
        }

        int[][] diff = new int[m + 1][n + 1];
        for (int i = stampHeight; i <= m; i++) {
            for (int j = stampWidth; j <= n; j++) {
                int r1 = i - stampHeight;
                int c1 = j - stampWidth;
                if (s[i][j] - s[i][c1] - s[r1][j] + s[r1][c1] == 0) {
                    diff[r1][c1]++;
                    diff[r1][j]--;
                    diff[i][c1]--;
                    diff[i][j]++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    diff[i][j] += diff[i - 1][j];
                }
                if (j > 0) {
                    diff[i][j] += diff[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    diff[i][j] -= diff[i - 1][j - 1];
                }
                if (grid[i][j] == 0 && diff[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
