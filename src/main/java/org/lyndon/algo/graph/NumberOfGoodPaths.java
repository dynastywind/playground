package org.lyndon.algo.graph;

import java.util.Arrays;

/**
 * LC2421
 */
public class NumberOfGoodPaths {

    private static class UnionFind {
        int[] vals;
        int[] parent;
        int[] size;

        public UnionFind(int[] vals, int n) {
            this.vals = vals;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public int union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (vals[ra] == vals[rb]) {
                int cnt = size[ra] * size[rb];
                parent[rb] = ra;
                size[ra] += size[rb];
                return cnt;
            } else if (vals[ra] > vals[rb]) {
                parent[rb] = ra;
                return -1;
            } else {
                parent[ra] = rb;
                return -1;
            }
        }
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        UnionFind uf = new UnionFind(vals, n);
        Arrays.sort(edges, (a, b) -> Integer.compare(Math.max(vals[a[0]], vals[a[1]]), Math.max(vals[b[0]], vals[b[1]])));
        int ans = n;
        for (int[] edge : edges) {
            int cnt = uf.union(edge[0], edge[1]);
            if (cnt > 0) {
                ans += cnt;
            }
        }
        return ans;
    }

}
