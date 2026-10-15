package org.lyndon.algo.graph;

import org.lyndon.utils.PrintUtils;

import java.util.LinkedList;
import java.util.List;

public class AllPathsFromSourceToTarget {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new LinkedList<>();
        dfs(graph, 0, new LinkedList<>(), paths);
        return paths;
    }

    private void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> paths) {
        path.add(node);
        if (node == graph.length - 1) {
            paths.add(path);
            return;
        }
        for (int v : graph[node]) {
            dfs(graph, v, new LinkedList<>(path), paths);
        }
    }

    public static void main(String[] args) {
        var solution = new AllPathsFromSourceToTarget();
        PrintUtils.printLists(solution.allPathsSourceTarget(new int[][]{new int[]{1, 2}, new int[]{3}, new int[]{3}, new int[]{}}));
        PrintUtils.printLists(solution.allPathsSourceTarget(new int[][]{new int[]{4, 3, 1}, new int[]{3, 2, 4}, new int[]{3}, new int[]{4}, new int[]{}}));
    }

}
