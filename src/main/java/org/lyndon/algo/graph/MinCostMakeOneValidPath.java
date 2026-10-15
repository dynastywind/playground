package org.lyndon.algo.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LC1368
 * Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * <p>
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some signs on the cells of the grid that point outside the grid.
 * <p>
 * You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.
 * <p>
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 * <p>
 * Return the minimum cost to make the grid have at least one valid path.
 */
public class MinCostMakeOneValidPath {

    /*
        0,0,0,1
        1,0,0,0
        0,0,0,1
        1,0,0,0
     */
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int w = cur[0];
            int x = cur[1];
            int y = cur[2];
            if (dist[x][y] < w) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                return w;
            }
            for (int i = 0; i < dirs.length; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                int nw = w + (i + 1 == grid[x][y] ? 0 : 1);
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (nw < dist[nx][ny]) {
                    dist[nx][ny] = nw;
                    pq.offer(new int[]{nw, nx, ny});
                }
            }
        }
        return dist[m - 1][n - 1];
    }

}
