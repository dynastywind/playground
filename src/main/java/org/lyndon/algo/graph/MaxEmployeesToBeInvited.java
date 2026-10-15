package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * LC2127
 */
public class MaxEmployeesToBeInvited {

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] indegree = new int[n];
        for (int f : favorite) {
            indegree[f]++;
        }

        int[] depth = new int[n];
        Arrays.fill(depth, 1);
        boolean[] removed = new boolean[n];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            removed[u] = true;
            int v = favorite[u];
            depth[v] = Math.max(depth[v], depth[u] + 1);
            if (--indegree[v] == 0) {
                queue.offer(v);
            }
        }

        int maxCycle = 0;
        int sumPairs = 0;
        for (int i = 0; i < n; i++) {
            if (removed[i]) {
                continue;
            }
            int len = 0;
            int node = i;
            while (!removed[node]) {
                removed[node] = true;
                node = favorite[node];
                len++;
            }
            if (len == 2) {
                sumPairs += depth[node] + depth[favorite[node]];
            } else {
                maxCycle = Math.max(maxCycle, len);
            }
        }
        return Math.max(maxCycle, sumPairs);
    }

}
