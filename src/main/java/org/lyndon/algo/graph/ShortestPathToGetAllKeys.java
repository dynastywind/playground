package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LC864
 */
public class ShortestPathToGetAllKeys {

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int sr = -1;
        int sc = -1;
        int allKeys = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    sr = i;
                    sc = j;
                } else if (c >= 'a' && c <= 'f') {
                    allKeys |= 1 << (c - 'a');
                }
            }
        }

        int mask = 1 << 6;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][][] visited = new boolean[m][n][mask];
        // [r, c, keys]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc, 0});
        visited[sr][sc][0] = true;

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                int keys = cur[2];
                if (keys == allKeys) {
                    return steps;
                }

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    char ch = grid[nr].charAt(nc);
                    if (ch == '#') {
                        continue;
                    }
                    if (ch >= 'A' && ch <= 'F' && ((keys >> (ch - 'A') & 1) == 0)) {
                        continue;
                    }
                    int nk = keys;
                    if (ch >= 'a' && ch <= 'f') {
                        nk |= 1 << (ch - 'a');
                    }
                    if (visited[nr][nc][nk]) {
                        continue;
                    }
                    visited[nr][nc][nk] = true;
                    queue.offer(new int[]{nr, nc, nk});
                }
            }
            steps++;
        }
        return -1;
    }

}
