package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LC2077
 */
public class PathsLeadToSameRoom {

    public int numberOfPaths(int n, int[][] corridors) {
        int[] degrees = new int[n + 1];
        for (int[] corridor : corridors) {
            degrees[corridor[0]]++;
            degrees[corridor[1]]++;
        }

        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] corridor : corridors) {
            int u = corridor[0];
            int v = corridor[1];
            // sort
            if ((degrees[u] > degrees[v]) || (degrees[u] == degrees[v] && u > v)) {
                int t = u;
                u = v;
                v = t;
            }
            graph[u].add(v);
        }

        int[] mark = new int[n + 1];
        int ans = 0;
        for (int u = 1; u <= n; u++) {
            for (int v : graph[u]) {
                mark[v] = u;
            }
            for (int v : graph[u]) {
                for (int w : graph[v]) {
                    if (mark[w] == u) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

}
