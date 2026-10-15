package org.lyndon.algo.graph;

import java.util.*;

public class JumpGameIV {

    public int minJumps(int[] arr) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            graph.putIfAbsent(arr[i], new HashSet<>());
            graph.get(arr[i]).add(i);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        int steps = 0;
        boolean[] visited = new boolean[arr.length];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                var index = queue.poll();
                if (index == arr.length - 1) {
                    return steps;
                }
                // same value nodes only consider once
                var neighbors = graph.remove(arr[index]);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
                if (index < arr.length - 1 && !visited[index + 1]) {
                    visited[index + 1] = true;
                    queue.add(index + 1);
                }
                if (index > 0 && !visited[index - 1]) {
                    visited[index - 1] = true;
                    queue.add(index - 1);
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        var solution = new JumpGameIV();
        System.out.println(solution.minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
        System.out.println(solution.minJumps(new int[]{7}));
        System.out.println(solution.minJumps(new int[]{7, 6, 9, 7}));
    }

}
