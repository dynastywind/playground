package org.lyndon.algo.graph;

/**
 * LC685
 */
public class RedundantConnectionII {

    private int[] parent;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] upper = new int[n + 1];
        int[] cand1 = null, cand2 = null;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // find nodes with indegree == 2
            if (upper[v] != 0) {
                cand1 = new int[]{upper[v], v};
                cand2 = edge;
                break;
            }
            upper[v] = u;
        }

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            if (edge == cand2) {
                continue;
            }
            int ru = find(edge[0]);
            int rv = find(edge[1]);
            // circle. If no indegree == 2, return current edge.
            if (ru == rv) {
                return cand1 == null ? edge : cand1;
            }
            parent[rv] = ru;
        }
        return cand2;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

}
