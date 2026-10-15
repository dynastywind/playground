package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LC3286
 */
public class FindASafeWalkThroughAGrid {

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.getFirst().size();
        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = grid.getFirst().getFirst();
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerFirst(0);

        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            int d = dist[cur];
            int r = cur / n;
            int c = cur % n;
            if (r == m - 1 && c == n - 1 && dist[cur] < health) {
                return true;
            }

            for (int[] dir : dirs) {
                int nr = dir[0] + r;
                int nc = dir[1] + c;
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                int nd = d + grid.get(nr).get(nc);
                if (nd >= health) {
                    continue;
                }
                int index = nr * n + nc;
                if (nd < dist[index]) {
                    dist[index] = nd;
                    if (grid.get(nr).get(nc) == 0) {
                        queue.offerFirst(index);
                    } else {
                        queue.offerLast(index);
                    }
                }
            }
        }
        return false;
    }

}
