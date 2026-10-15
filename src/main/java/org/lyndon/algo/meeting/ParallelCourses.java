package org.lyndon.algo.meeting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LC1136
 */
public class ParallelCourses {

    public int minimumSemesters(int n, int[][] relations) {
        int[] degrees = new int[n];
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] relation : relations) {
            graph[relation[0] - 1].add(relation[1] - 1);
            degrees[relation[1] - 1]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 0) {
                queue.offer(i);
            }
        }
        if (queue.isEmpty()) {
            return -1;
        }
        int stages = 0;
        List<Integer> topology = new ArrayList<>();
        while (!queue.isEmpty()) {
            stages++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                topology.add(u);
                for (int v : graph[u]) {
                    degrees[v]--;
                    if (degrees[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }
        return topology.size() == n ? stages : -1;
    }

}
