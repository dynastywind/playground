package org.lyndon.algo.graph;

import java.util.Arrays;

/**
 * LC2123
 */
public class MinOpsToRemoveAdjOnes {

    private int round;

    public int minimumOperations(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] matchY = new int[m * n];
        Arrays.fill(matchY, -1);
        int[] visited = new int[m * n];

        final int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (((i + j) & 1) == 0 && grid[i][j] == 1) {
                    round++;
                    if (augment(i, j, m, n, grid, matchY, visited, dirs)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private boolean augment(int i, int j, int m, int n, int[][] grid, int[] matchY, int[] visited, int[][] dirs) {
        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (ni < 0 || ni >= m || nj < 0 || nj >= n || grid[ni][nj] == 0) {
                continue;
            }
            int id = ni * n + nj;
            if (visited[id] == round) {
                continue;
            }
            visited[id] = round;
            if (matchY[id] == -1 || augment(matchY[id] / n, matchY[id] % n, m, n, grid, matchY, visited, dirs)) {
                matchY[id] = i * n + j;
                return true;
            }
        }
        return false;
    }

}
