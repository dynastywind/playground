package org.lyndon.algo.graph;

import java.util.PriorityQueue;

public class MouseToCheese {

    public int masSafety(String[] grid) {
        final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int n = grid.length;
        int cr = -1;
        int cc = -1;
        int s = -1;
        int t = -1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                char ch = grid[r].charAt(c);
                if (ch == 'C') {
                    cr = r;
                    cc = c;
                } else if (ch == 'S') {
                    s = r * n + c;
                } else if (ch == 'T') {
                    t = r * n + c;
                }
            }
        }

        int[] dist = new int[n * n];
        // {dist, index}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{manhattan(s / n, s % n, cr, cc), s});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int id = cur[1];
            if (d < dist[id]) {
                continue;
            }
            if (id == t) {
                return dist[id];
            }
            for (int[] dir : dirs) {
                int nr = id / n + dir[0];
                int nc = id % n + dir[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n || grid[nr].charAt(nc) == 'W') {
                    continue;
                }
                int nd = Math.min(d, manhattan(nr, nc, cr, cc));
                int nid = nr * n + nc;
                if (nd > dist[nid]) {
                    dist[nid] = nd;
                    pq.offer(new int[]{nd, nid});
                }
            }
        }
        return -1;
    }

    private int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
