package org.lyndon.algo.graph;

import java.util.Arrays;

/**
 * LC1135
 */
public class ConnectingCitiesWithMinCost {

    private int[] parent;

    public int minimumCost(int n, int[][] connections) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        Arrays.sort(connections, (a, b) -> Integer.compare(a[2], b[2]));
        int ans = 0;
        int nodes = n;
        for (int[] conn : connections) {
            int a = conn[0] - 1;
            int b = conn[1] - 1;
            if (find(a) == find(b)) {
                continue;
            }
            parent[find(a)] = find(b);
            int cost = conn[2];
            ans += cost;
            if (--nodes == 1) {
                break;
            }
        }
        return nodes == 1 ? ans : -1;
    }

    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

}
