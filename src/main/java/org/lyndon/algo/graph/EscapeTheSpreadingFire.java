package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * LC2258
 */
public class EscapeTheSpreadingFire {

    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[][] fire = new int[m][n];
        for (int[] f : fire) {
            Arrays.fill(f, Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fire[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] == 2 || fire[nr][nc] != Integer.MAX_VALUE) {
                    continue;
                }
                fire[nr][nc] = fire[r][c] + 1;
                queue.add(new int[]{nr, nc});
            }
        }

        int lo = 0;
        int hi = 1_000_000_000;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(grid, fire, m, n, dirs, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[][] grid, int[][] fire, int m, int n, int[][] dirs, int t) {
        if (fire[0][0] <= t) {
            return false;
        }
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.offer(new int[]{0, 0});
        int time = t;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] == 2 || visited[nr][nc]) {
                        continue;
                    }
                    if (nr == m - 1 && nc == n - 1) {
                        if (time <= fire[nr][nc]) {
                            return true;
                        }
                        continue;
                    }
                    if (time >= fire[nr][nc]) {
                        continue;
                    }
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }

}
