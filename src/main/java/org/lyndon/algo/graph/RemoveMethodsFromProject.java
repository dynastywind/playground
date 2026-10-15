package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LC3310
 */
public class RemoveMethodsFromProject {

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] invocation : invocations) {
            graph[invocation[0]].add(invocation[1]);
        }
        boolean[] suspicious = new boolean[n];
        dfs(graph, k, suspicious);

        boolean canRemove = true;
        for (int[] invocation : invocations) {
            int u = invocation[0];
            int v = invocation[1];
            if (!suspicious[u] && suspicious[v]) {
                canRemove = false;
                break;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!canRemove || !suspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    private void dfs(List<Integer>[] graph, int u, boolean[] suspicious) {
        if (suspicious[u]) {
            return;
        }
        suspicious[u] = true;
        for (int v : graph[u]) {
            dfs(graph, v, suspicious);
        }
    }

}
