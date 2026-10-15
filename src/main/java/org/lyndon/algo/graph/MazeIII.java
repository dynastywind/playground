package org.lyndon.algo.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LC499
 */
public class MazeIII {

    private static class State {
        int x;
        int y;
        int dist;
        String path;

        public State(int x, int y, int dist, String path) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.path = path;
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        final Map<String, int[]> dirs = new HashMap<>();
        dirs.put("l", new int[]{0, -1});
        dirs.put("r", new int[]{0, 1});
        dirs.put("u", new int[]{-1, 0});
        dirs.put("d", new int[]{1, 0});
        int m = maze.length;
        int n = maze[0].length;

        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[ball[0]][ball[1]] = 0;
        String[][] paths = new String[m][n];
        paths[ball[0]][ball[1]] = "";
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> {
            if (a.dist != b.dist) {
                return Integer.compare(a.dist, b.dist);
            }
            return a.path.compareTo(b.path);
        });
        pq.offer(new State(ball[0], ball[1], 0, ""));
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            if (cur.dist > dist[x][y] || cur.path.compareTo(paths[x][y]) > 0) {
                continue;
            }
            if (x == hole[0] && y == hole[1]) {
                return cur.path;
            }
            for (Map.Entry<String, int[]> entry : dirs.entrySet()) {
                String label = entry.getKey();
                int[] dir = entry.getValue();
                int nx = x;
                int ny = y;
                int d = 0;
                while (nx + dir[0] >= 0 && nx + dir[0] < m && ny + dir[1] >= 0 && ny + dir[1] < n && maze[nx + dir[0]][ny + dir[1]] == 0) {
                    nx += dir[0];
                    ny += dir[1];
                    d++;
                    if (nx == hole[0] && ny == hole[1]) {
                        break;
                    }

                }
                if (d == 0) {
                    continue;
                }
                String np = cur.path + label;
                int nd = d + cur.dist;
                if (nd < dist[nx][ny] || (nd == dist[nx][ny] && np.compareTo(paths[nx][ny]) < 0)) {
                    dist[nx][ny] = nd;
                    paths[nx][ny] = np;
                    pq.offer(new State(nx, ny, nd, np));
                }
            }
        }
        return "impossible";
    }

}
