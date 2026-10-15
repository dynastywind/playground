package org.lyndon.algo.graph;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * LC1260
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 * <p>
 * In one shift operation:
 * <p>
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying shift operation k
 */
public class Shift2DGrid {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                l.add(0);
            }
            list.add(l);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] next = to(i, j, m, n, k);
                int x = next[0];
                int y = next[1];
                list.get(x).set(y, grid[i][j]);
            }
        }
        return list;
    }

    private int[] to(int x, int y, int m, int n, int k) {
        return new int[]{((y + k) / n + x) % m, (y + k % n) % n};
    }

    public static void main(String[] args) {
        var solution = new Shift2DGrid();
        PrintUtils.printLists(solution.shiftGrid(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 2));
        PrintUtils.printLists(solution.shiftGrid(new int[][]{{1, 2}, {4, 5}, {7, 8}}, 3));
    }

}
