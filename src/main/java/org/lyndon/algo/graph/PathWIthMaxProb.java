package org.lyndon.algo.graph;

import java.util.*;

public class PathWIthMaxProb {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<double[]>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int target = edges[i][1];
            double dist = dist(succProb[i]);
            graph.get(source).add(new double[]{target, dist});
            graph.get(target).add(new double[]{source, dist});
        }

        PriorityQueue<double[]> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> o[1]));
        queue.add(new double[]{start_node, 0});
        double[] dist = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[start_node] = 0;
        while (!queue.isEmpty()) {
            double[] cur = queue.poll();
            int node = (int) cur[0];
            if (cur[1] > dist[node]) {
                continue;
            }
            for (double[] nb : graph.get(node)) {
                double nd = cur[1] + nb[1];
                int targetNode = (int) nb[0];
                if (nd < dist[targetNode]) {
                    dist[targetNode] = nd;
                    queue.add(new double[]{targetNode, nd});
                }
            }
        }
        return Math.exp(-dist[end_node]);
    }

    private double dist(double prob) {
        return -Math.log(prob);
    }

    public static void main(String[] args) {
        var solution = new PathWIthMaxProb();
        System.out.println(solution.maxProbability(3, new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
        System.out.println(solution.maxProbability(3, new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{0, 2}}, new double[]{0.5, 0.5, 0.4}, 0, 2));
    }

}
