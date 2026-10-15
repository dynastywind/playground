package org.lyndon.algo.graph;

import java.util.*;
import java.util.stream.Collectors;

public class CountPaths {

    private static int countPaths(int n, int[][] roads) {
        List<long[]>[] graph = new ArrayList[n];
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int weight = road[2];
            if (graph[u] == null) {
                graph[u] = new ArrayList<>();
            }
            if (graph[v] == null) {
                graph[v] = new ArrayList<>();
            }
            graph[u].add(new long[]{v, weight});
            graph[v].add(new long[]{u, weight});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        long[] paths = new long[n];
        paths[0] = 1;

        PriorityQueue<long[]> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        queue.add(new long[]{0, 0});
        while (!queue.isEmpty()) {
            var node = queue.poll();
            int u = (int) node[0];
            long cost = node[1];
            if (dist[u] < cost) {
                continue;
            }
            if (graph[u] == null) {
                continue;
            }
            for (long[] nb : graph[u]) {
                int v = (int) nb[0];
                long nw = cost + nb[1];
                if (nw < dist[v]) {
                    dist[v] = nw;
                    paths[v] = paths[u];
                    queue.add(new long[]{v, dist[v]});
                } else if (nw == dist[v]) {
                    paths[v] = (paths[u] + paths[v]) % 1_000_000_007;
                }
            }
        }

        for (List<Integer> path : allShortestPaths(graph, dist)) {
            System.out.println(path.stream().map(i -> "" + i).collect(Collectors.joining("->")));
        }
        return (int) paths[n - 1];
    }

    private static List<List<Integer>> allShortestPaths(List<long[]>[] graph, long[] dist) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        path.addLast(0);
        dfs(0, graph.length - 1, graph, dist, path, result);
        return result;
    }

    private static void dfs(int u, int target, List<long[]>[] graph, long[] dist, List<Integer> path, List<List<Integer>> result) {
        if (u == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (long[] nb : graph[u]) {
            int v = (int) nb[0];
            long nw = dist[u] + nb[1];
            if (nw == dist[v]) {
                path.addLast(v);
                dfs(v, target, graph, dist, path, result);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(countPaths(7, new int[][]{
                new int[]{0, 6, 7},
                new int[]{0, 1, 2},
                new int[]{1, 2, 3},
                new int[]{1, 3, 3},
                new int[]{6, 3, 3},
                new int[]{3, 5, 1},
                new int[]{6, 5, 1},
                new int[]{2, 5, 1},
                new int[]{0, 4, 5},
                new int[]{4, 6, 2}}));
        System.out.println(countPaths(1, new int[][]{}));
    }

}
