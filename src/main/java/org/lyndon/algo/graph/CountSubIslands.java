package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC1905
 */
public class CountSubIslands {

    private int[] parent;
    private int[] rank;

    private int find(int x) {
        if (parent[x] != x) {
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
        if (rank[ra] < rank[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }
        parent[rb] = ra;
        if (rank[ra] == rank[rb]) {
            rank[ra]++;
        }
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        parent = new int[m * n];
        rank = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = key(i, j, n);
                parent[key] = key;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 0) {
                    continue;
                }
                int key = key(i, j, n);
                if (i < m - 1 && grid2[i + 1][j] == 1) {
                    union(key, key(i + 1, j, n));
                }
                if (j < n - 1 && grid2[i][j + 1] == 1) {
                    union(key, key(i, j + 1, n));
                }
            }
        }
        Map<Integer, List<Integer>> islands = new HashMap<>();
        for (int i = 0; i < m * n; i++) {
            int root = find(i);
            int[] pos = index(i, n);
            if (grid2[pos[0]][pos[1]] == 1) {
                islands.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
            }
        }

        int ans = 0;
        for (List<Integer> island : islands.values()) {
            boolean valid = true;
            for (int i : island) {
                int[] pos = index(i, n);
                if (grid1[pos[0]][pos[1]] == 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans++;
            }
        }
        return ans;
    }

    private int key(int r, int c, int n) {
        return r * n + c;
    }

    private int[] index(int key, int n) {
        return new int[]{key / n, key % n};
    }

    public static void main(String[] args) {
        var solution = new CountSubIslands();
        System.out.println(solution.countSubIslands(new int[][]{{1, 1, 1, 0, 0}, {0, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 1, 1}},
                new int[][]{{1, 1, 1, 0, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}}));
        System.out.println(solution.countSubIslands(new int[][]{{1, 0, 1, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 1, 0, 1}},
                new int[][]{{0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}}));
    }

}
