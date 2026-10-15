package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LC296
 * Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
 * <p>
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 * <p>
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 */
public class BestMeetingPoint {

    public int minTotalDistance(int[][] grid) {
        return minDistance(collectRows(grid)) + minDistance(collectCols(grid));
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    res.add(j);
                }
            }
        }
        return res;
    }

    private int minDistance(List<Integer> list) {
        int i = 0;
        int j = list.size() - 1;
        int dist = 0;
        while (i < j) {
            dist += list.get(j--) - list.get(i++);
        }
        return dist;
    }

}
