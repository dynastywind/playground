package org.lyndon.algo.graph;

import java.util.Arrays;

/**
 * LC1101
 */
public class EarliestMomentWhenEveryoneBecomeFriends {

    private static class UnionFind {
        int[] parent;
        int size;

        public UnionFind(int n) {
            parent = new int[n];
            size = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return;
            }
            parent[ra] = rb;
            size--;
        }
    }

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));
        UnionFind uf = new UnionFind(n);
        for (int[] log : logs) {
            uf.union(log[1], log[2]);
            if (uf.size == 1) {
                return log[0];
            }
        }
        return -1;
    }

}
