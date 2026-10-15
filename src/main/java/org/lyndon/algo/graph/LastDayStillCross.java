package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LC1970
 */
public class LastDayStillCross {

    private int[] parent;
    private int[] rank;

    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = row * col;
        int top = n, bottom = n + 1;
        parent = new int[n + 2];
        for (int i = 0; i < n + 2; i++) {
            parent[i] = i;
        }
        rank = new int[n + 2];

        final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[] land = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            int id = r * col + c;
            land[id] = true;

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                int nid = nr * col + nc;
                if (nr < 0 || nr >= row || nc < 0 || nc >= col || !land[nid]) {
                    continue;
                }
                union(id, nid);
            }
            if (r == 0) {
                union(id, top);
            }
            if (r == row - 1) {
                union(id, bottom);
            }
            if (find(top) == find(bottom)) {
                return i;
            }
        }
        return 0;
    }

    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return;
        }
        if (rank[ra] < rank[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }
        parent[rb] = ra;
        if (rank[ra] == rank[rb]) {
            rank[ra]++;
        }
    }

    public int latestDayToCrossBinarySearch(int row, int col, int[][] cells) {
        int lo = 0;
        int hi = cells.length;
        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(row, col, cells, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int row, int col, int[][] cells, int day) {
        boolean[][] blocked = new boolean[row][col];
        for (int i = 0; i < day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            blocked[r][c] = true;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int c = 0; c < col; c++) {
            if (!blocked[0][c]) {
                queue.offer(new int[]{0, c});
                blocked[0][c] = true;
            }
        }
        final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            if (r == row - 1) {
                return true;
            }
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= row || nc < 0 || nc >= col || blocked[nr][nc]) {
                    continue;
                }
                queue.offer(new int[]{nr, nc});
                blocked[nr][nc] = true;
            }
        }
        return false;
    }

}
