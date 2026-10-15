package org.lyndon.algo.graph;

/**
 * LC1254
 */
public class NumberOfClosedIslands {

    private int[] parent;
    private int size;
    private int vId;

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        vId = m * n;
        parent = new int[vId + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = key(i, j, n);
                parent[key] = key;
                if (grid[i][j] == 0) {
                    size++;
                }
            }
        }
        parent[vId] = vId;

        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 || visited[i][j]) {
                    continue;
                }
                int key = key(i, j, n);
                visited[i][j] = true;
                for (int[] dir : dirs) {
                    int nr = i + dir[0];
                    int nc = j + dir[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        union(vId, key);
                    } else if (grid[nr][nc] != 1 && !visited[nr][nc]) {
                        union(key(nr, nc, n), key);
                    }
                }
            }
        }
        return size;
    }

    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return;
        }
        if (ra == vId) {
            int t = ra;
            ra = rb;
            rb = t;
        }
        parent[ra] = rb;
        size--;
    }

    private int key(int r, int c, int n) {
        return r * n + c;
    }

}
