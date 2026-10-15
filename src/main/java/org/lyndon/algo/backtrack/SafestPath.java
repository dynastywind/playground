package org.lyndon.algo.backtrack;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LC2812
 */
public class SafestPath {

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        if (grid.get(0).get(0) == 1) {
            return 0;
        }
        int[][] dir = new int[][]{new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1}};
        int m = grid.size();
        int n = grid.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.add(new int[]{i, j});
                } else {
                    dist[i][j] = -1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int[] d : dir) {
                int x = pos[0] + d[0];
                int y = pos[1] + d[1];
                if (x < 0 || y < 0 || x > m - 1 || y > n - 1) {
                    continue;
                }
                if (dist[x][y] == -1) {
                    dist[x][y] = dist[pos[0]][pos[1]] + 1;
                    queue.add(new int[]{x, y});
                }
            }
        }

        // binary search
        int low = 0;
        int high = Math.min(dist[0][0], dist[m - 1][n - 1]);
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (check(dist, mid, m, n, dir)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[][] dist, int limit, int m, int n, int[][] dir) {
        if (dist[0][0] < limit) {
            return false;
        }
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            if (pos[0] == m - 1 && pos[1] == n - 1) {
                return true;
            }
            for (int[] d : dir) {
                int x = pos[0] + d[0];
                int y = pos[1] + d[1];
                if (x < 0 || y < 0 || x > m - 1 || y > n - 1) {
                    continue;
                }
                if (visited[x][y]) {
                    continue;
                }
                if (dist[x][y] < limit) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var solution = new SafestPath();
        System.out.println(solution.maximumSafenessFactor(List.of(List.of(0, 1, 1), List.of(0, 1, 1), List.of(0, 1, 0))));
    }

}
