package org.lyndon.algo.tree;

import java.util.*;

/**
 * LC3812
 */
public class MinEdgeTogglesOnATree {

    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        // {v, edge_id}
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph[a].add(new int[]{b, i});
            graph[b].add(new int[]{a, i});
        }

        int[] order = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] parentEdge = new int[n];
        int idx = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[idx++] = u;
            for (int[] e : graph[u]) {
                int v = e[0];
                if (v == parent[u]) {
                    continue;
                }
                parent[v] = u;
                parentEdge[v] = e[1];
                queue.offer(v);
            }
        }

        boolean[] need = new boolean[n];
        for (int v = 0; v < n; v++) {
            need[v] = start.charAt(v) != target.charAt(v);
        }

        boolean[] chosen = new boolean[n - 1];
        for (int k = n - 1; k > 0; k--) {
            int v = order[k];
            if (need[v]) {
                chosen[parentEdge[v]] = true;
                need[parent[v]] = !need[parent[v]];
            }
        }

        if (need[0]) {
            return List.of(-1);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (chosen[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

}
