package org.lyndon.algo.tree;

import java.util.*;

/**
 * LC310
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 * <p>
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * <p>
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * <p>
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MHT {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
            degree[u]++;
            degree[v]++;
        }

        int remaining = n;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty() && remaining > 2) {
            int size = queue.size();
            remaining -= size;
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                for (int v : graph[u]) {
                    if (--degree[v] == 1) {
                        queue.offer(v);
                    }
                }
            }
        }
        return new ArrayList<>(queue);
    }

}
