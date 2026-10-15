package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC815
 */
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }
        if (!stopToRoutes.containsKey(source) || !stopToRoutes.containsKey(target)) {
            return -1;
        }

        boolean[] usedRoutes = new boolean[n];
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited.add(source);

        int ans = 0;
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int route : stopToRoutes.get(cur)) {
                    if (usedRoutes[route]) {
                        continue;
                    }
                    usedRoutes[route] = true;
                    for (int next : routes[route]) {
                        if (next == target) {
                            return ans;
                        }
                        if (visited.add(next)) {
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return -1;
    }

}
