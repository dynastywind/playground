package org.lyndon.algo.array;

/**
 * LC3240
 * You are given an m x n binary matrix grid.
 * <p>
 * A row or column is considered palindromic if its values read the same forward and backward.
 * <p>
 * You can flip any number of cells in grid from 0 to 1, or from 1 to 0.
 * <p>
 * Return the minimum number of cells that need to be flipped to make all rows and columns palindromic, and the total number of 1's in grid divisible by 4
 */
public class MinNumberOfFlipsMakePalindromicBinaryGrid {

    public int minFlips(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        // four-element symmetric groups. Contribute nothing for mod 4.
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int cnt = grid[i][j] + grid[i][n - 1 - j] + grid[m - 1 - i][j] + grid[m - 1 - i][n - 1 - j];
                ans += Math.min(cnt, 4 - cnt);
            }
        }

        // central
        if (m % 2 == 1 && n % 2 == 1) {
            ans += grid[m / 2][n / 2];
        }

        // central line/column
        int cnt1 = 0;
        int diff = 0;
        if (m % 2 == 1) {
            for (int j = 0; j < n / 2; j++) {
                if (grid[m / 2][j] == grid[m / 2][n - 1 - j]) {
                    cnt1 += 2 * grid[m / 2][j];
                } else {
                    diff++;
                }
            }
        }
        if (n % 2 == 1) {
            for (int i = 0; i < m / 2; i++) {
                if (grid[i][n / 2] == grid[m - 1 - i][n / 2]) {
                    cnt1 += 2 * grid[i][n / 2];
                } else {
                    diff++;
                }
            }
        }
        // flip 2 if no diff and cannot be divided by 4
        ans += (cnt1 % 4 == 0 || diff > 0) ? diff : 2;
        return ans;
    }

}
