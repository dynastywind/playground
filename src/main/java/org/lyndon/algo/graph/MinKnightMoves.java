package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LC1197
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * <p>
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.
 * <p>
 * Example 1:
 * <p>
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 * Example 2:
 * <p>
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 * <p>
 * Constraints:
 * <p>
 * -300 <= x, y <= 300
 * 0 <= |x| + |y| <= 300
 */
public class MinKnightMoves {

    public int minKnightMoves(int x, int y) {
        int[][] dirs = new int[][]{{1, 2}, {-1, 2}, {-1, -2}, {1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        final int lo = -2;
        final int hi = 302;
        final int SIZE = hi - lo + 1;
        boolean[][] visited = new boolean[SIZE][SIZE];
        visited[-lo][-lo] = true;
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int p = cur[0];
                int q = cur[1];
                if (p == x && q == y) {
                    return steps;
                }
                for (int[] dir : dirs) {
                    int np = p + dir[0];
                    int nq = q + dir[1];
                    if (np < lo || np > hi || nq < lo || nq > hi || visited[np - lo][nq - lo]) {
                        continue;
                    }
                    visited[np - lo][nq - lo] = true;
                    queue.offer(new int[]{np, nq});
                }
            }
            steps++;
        }
        return steps;
    }

}
