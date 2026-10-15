package org.lyndon.algo.graph;

/**
 * LC1559
 */
public class CyclesInGrid {

    private int[] parent;
    private int[] rank;

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        rank = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i * n + j;
                if (j < n - 1 && grid[i][j] == grid[i][j + 1]) {
                    if (!union(id, id + 1)) {
                        return true;
                    }
                }
                if (i < m - 1 && grid[i][j] == grid[i + 1][j]) {
                    if (!union(id, id + n)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return false;
        }
        if (rank[ra] < rank[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }
        parent[rb] = ra;
        if (rank[ra] == rank[rb]) {
            rank[ra]++;
        }
        return true;
    }

    public boolean containsCycleDFS(char[][] grid) {
        final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && dfs(grid, i, j, m, n, -1, -1, visited, dirs)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, int r, int c, int m, int n, int pr, int pc, boolean[][] visited, int[][] dirs) {
        visited[r][c] = true;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                continue;
            }
            if (grid[nr][nc] != grid[r][c]) {
                continue;
            }
            if (nr == pr && nc == pc) {
                continue;
            }
            if (visited[nr][nc]) {
                return true;
            }
            if (dfs(grid, nr, nc, m, n, r, c, visited, dirs)) {
                return true;
            }
        }
        return false;
    }

}
