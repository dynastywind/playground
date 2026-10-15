package org.lyndon.algo.graph.eulerian;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LC3568
 */
public class MinMovesToCleanClassroom {

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] id = new int[m][n];
        int ls = 0;
        int sx = -1;
        int sy = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    id[i][j] = ls++;
                }
            }
        }
        if (ls == 0) {
            return 0;
        }

        final int full = 1 << ls;
        int[][][] dist = new int[m][n][full];
        for (int[][] a : dist) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        dist[sx][sy][0] = energy;

        // [x, y, energy, mask]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy, energy, 0});
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int e = cur[2];
                int mask = cur[3];
                if (mask == full - 1) {
                    return steps;
                }
                if (e == 0) {
                    continue;
                }
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    char c = classroom[nx].charAt(ny);
                    if (c == 'X') {
                        continue;
                    }
                    int ne = c == 'R' ? energy : e - 1;
                    int nm = c == 'L' ? mask | (1 << id[nx][ny]) : mask;
                    if (ne <= dist[nx][ny][nm]) {
                        continue;
                    }
                    dist[nx][ny][nm] = ne;
                    queue.offer(new int[]{nx, ny, ne, nm});
                }
            }
            steps++;
        }
        return -1;
    }

}
