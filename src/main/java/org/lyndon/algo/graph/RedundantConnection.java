package org.lyndon.algo.graph;

/**
 * LC684
 */
public class RedundantConnection {

    private static class UnionFind {
        private int[] parents;
        private int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public int find(int x) {
            int root = x;
            while (root != parents[root]) {
                root = parents[root];
            }
            while (x != root) {
                int p = parents[x];
                parents[x] = root;
                x = p;
            }
            return root;
        }

        public boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return false;
            }
            if (ranks[ra] < ranks[rb]) {
                int tmp = ra;
                ra = rb;
                rb = tmp;
            }
            parents[rb] = ra;
            if (ranks[ra] == ranks[rb]) {
                ranks[ra]++;
            }
            return true;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            if (!uf.union(u, v)) {
                return new int[]{u + 1, v + 1};
            }
        }
        return null;
    }

}
