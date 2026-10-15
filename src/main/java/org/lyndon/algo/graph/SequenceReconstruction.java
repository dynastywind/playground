package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LC444
 */
public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        int n = nums.length;
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[n + 1];
        for (List<Integer> sequence : sequences) {
            for (int i = 0; i + 1 < sequence.size(); i++) {
                int u = sequence.get(i);
                int v = sequence.get(i + 1);
                graph[u].add(v);
                indegree[v]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return false;
            }
            int u = queue.poll();
            if (index == n || nums[index++] != u) {
                return false;
            }
            for (int v : graph[u]) {
                if (--indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return index == n;
    }

    public boolean hamilton(int[] nums, List<List<Integer>> sequences) {
        int n = nums.length;
        int[] pos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pos[nums[i]] = i;
        }

        boolean[] adjCovered = new boolean[n];
        for (List<Integer> sequence : sequences) {
            for (int i = 0; i + 1 < sequence.size(); i++) {
                int a = pos[sequence.get(i)];
                int b = pos[sequence.get(i + 1)];
                if (a >= b) {
                    return false;
                }
                if (b - a == 1) {
                    adjCovered[a] = true;
                }
            }
        }

        for (int i = 0; i + 1 < n; i++) {
            if (!adjCovered[i]) {
                return false;
            }
        }
        return true;
    }

}
