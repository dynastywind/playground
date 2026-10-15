package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LC1926
 */
public class NearestExit {

    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        for (int i = 0; i < m; i++) {
            if (maze[i][0] == '.') {
                maze[i][0] = '#';
            }
            if (maze[i][n - 1] == '.') {
                maze[i][n - 1] = '#';
            }
        }
        for (int j = 0; j < n; j++) {
            if (maze[0][j] == '.') {
                maze[0][j] = '#';
            }
            if (maze[n - 1][j] == '.') {
                maze[n - 1][j] = '#';
            }
        }
        maze[entrance[0]][entrance[1]] = '.';
        final int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{entrance[0], entrance[1]});
        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || maze[nr][nc] == '+') {
                        continue;
                    }
                    if (maze[nr][nc] == '#') {
                        return steps;
                    }
                    maze[nr][nc] = '+';
                    queue.offer(new int[]{nr, nc});
                }
            }
            steps++;
        }
        return -1;
    }

}
