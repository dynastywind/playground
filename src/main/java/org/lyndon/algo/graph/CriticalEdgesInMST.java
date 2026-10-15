package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC1489
 */
public class CriticalEdgesInMST {

    private static class UnionFind {
        private final int[] parent, rank;

        UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
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
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] e = new int[m][4];
        for (int i = 0; i < m; i++) {
            e[i] = new int[]{edges[i][0], edges[i][1], edges[i][2], i};
        }
        Arrays.sort(e, (a, b) -> Integer.compare(a[2], b[2]));

        int base = kruskal(n, e, -1, -1);
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (kruskal(n, e, i, -1) > base) {
                critical.add(e[i][3]);
            } else if (kruskal(n, e, -1, i) == base) {
                pseudoCritical.add(e[i][3]);
            }
        }
        return Arrays.asList(critical, pseudoCritical);
    }

    private int kruskal(int n, int[][] edges, int skip, int force) {
        UnionFind uf = new UnionFind(n);
        int weight = 0;
        int cnt = 0;
        if (force != -1) {
            uf.union(edges[force][0], edges[force][1]);
            weight += edges[force][2];
            cnt++;
        }
        for (int i = 0; i < edges.length && cnt < n - 1; i++) {
            if (skip == i) {
                continue;
            }
            if (uf.union(edges[i][0], edges[i][1])) {
                weight += edges[i][2];
                cnt++;
            }
        }
        return cnt == n - 1 ? weight : Integer.MAX_VALUE;
    }

}
