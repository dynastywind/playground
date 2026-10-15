package org.lyndon.algo.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * LC827
 */
public class MakingALargeIsland {

    private static class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            int root = x;
            while (root != parent[root]) {
                root = parent[root];
            }
            while (x != root) {
                int tmp = parent[x];
                parent[x] = root;
                x = tmp;
            }
            return root;
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return;
            }
            if (size[ra] < size[rb]) {
                int tmp = ra;
                ra = rb;
                rb = tmp;
            }
            parent[rb] = ra;
            size[ra] += size[rb];
        }
    }

    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                if (i < m - 1 && grid[i + 1][j] == 1) {
                    uf.union(key(i, j, n), key(i + 1, j, n));
                }
                if (j < n - 1 && grid[i][j + 1] == 1) {
                    uf.union(key(i, j, n), key(i, j + 1, n));
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m * n; i++) {
            if (grid[i / n][i % n] == 0) {
                continue;
            }
            ans = Math.max(ans, uf.size[uf.find(i)]);
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }
                roots.clear();
                int sum = 1;
                for (int[] dir : dirs) {
                    int nr = i + dir[0];
                    int nc = j + dir[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] == 0) {
                        continue;
                    }
                    int root = uf.find(key(nr, nc, n));
                    if (roots.add(root)) {
                        sum += uf.size[root];
                    }
                }
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }

    private int key(int r, int c, int n) {
        return r * n + c;
    }

}
