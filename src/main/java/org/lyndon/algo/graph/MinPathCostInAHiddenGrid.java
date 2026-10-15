package org.lyndon.algo.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC1810
 */
public class MinPathCostInAHiddenGrid {

    private interface GridMaster {
        boolean canMove(char direction);

        int move(char direction);

        boolean isTarget();
    }

    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final char[] DIR = {'U', 'D', 'L', 'R'};
    private static final char[] INVERSE_DIR = {'D', 'U', 'R', 'L'};
    private static final int N = 201;
    private static final int S = 100;

    private final int[][] cost = new int[N][N];
    private final boolean[][] visited = new boolean[N][N];
    private int tr = -1, tc = -1;

    public int findShortestPath(GridMaster master) {
        visited[S][S] = true;
        cost[S][S] = 0;
        dfs(master, S, S);
        if (tr == -1) {
            return -1;
        }
        return dijkstra(master);
    }

    private void dfs(GridMaster m, int r, int c) {
        if (m.isTarget()) {
            tr = r;
            tc = c;
        }
        for (int i = 0; i < dirs.length; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];
            if (visited[nr][nc]) {
                continue;
            }
            if (!m.canMove(DIR[i])) {
                continue;
            }
            visited[nr][nc] = true;
            cost[nr][nc] = m.move(DIR[i]);
            dfs(m, nr, nc);
            m.move(INVERSE_DIR[i]);
        }
    }

    private int dijkstra(GridMaster m) {
        int[][] dist = new int[N][N];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[S][S] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, S, S});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int r = cur[1];
            int c = cur[2];
            if (dist[r][c] < d) {
                continue;
            }
            if (r == tr && c == tc) {
                return d;
            }
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                if (!visited[nr][nc]) {
                    continue;
                }
                int nd = d + cost[nr][nc];
                if (nd < dist[nr][nc]) {
                    dist[nr][nc] = nd;
                    pq.offer(new int[]{nd, nr, nc});
                }
            }
        }
        return -1;
    }

}
