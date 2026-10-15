package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LC1192
 */
public class CriticalConnectionsInANetwork {

    private int timer = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> conn : connections) {
            int u = conn.get(0);
            int v = conn.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        int[] dfn = new int[n];
        int[] low = new int[n];
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfn[i] == 0) {
                tarjan(i, -1, dfn, low, graph, result);
            }
        }
        return result;
    }

    private void tarjan(int u, int parent, int[] dfn, int[] low, List<Integer>[] graph, List<List<Integer>> result) {
        dfn[u] = low[u] = ++timer;
        for (int v : graph[u]) {
            if (v == parent) {
                continue;
            }
            if (dfn[v] == 0) {
                tarjan(v, u, dfn, low, graph, result);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > dfn[u]) {
                    result.add(List.of(u, v));
                }
            } else {
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }

}
