package org.lyndon.algo.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC733
 */
public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (color == image[sr][sc]) {
            return image;
        }
        int m = image.length;
        int n = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        boolean[][] visited = new boolean[m][n];
        visited[sr][sc] = true;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int originalColor = image[sr][sc];
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            image[r][c] = color;
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc] || image[nr][nc] != originalColor) {
                    continue;
                }
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
        return image;
    }

}
