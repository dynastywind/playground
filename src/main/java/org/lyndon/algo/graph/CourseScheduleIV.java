package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC1462
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.
 * <p>
 * For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
 * Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
 * <p>
 * You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
 * <p>
 * Return a boolean array answer, where answer[j] is the answer to the jth query.
 */
public class CourseScheduleIV {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        BitSet[] prev = new BitSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
            prev[i] = new BitSet(numCourses);
        }
        int[] inDegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph.get(u)) {
                prev[v].or(prev[u]);
                prev[v].set(u);
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];
            ans.add(prev[v].get(u));
        }
        return ans;
    }

}
