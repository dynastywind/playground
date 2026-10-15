package org.lyndon.algo.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LC210
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 * <p>
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 * <p>
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            if (graph[pre[1]] == null) {
                graph[pre[1]] = new ArrayList<>();
            }
            graph[pre[1]].add(pre[0]);
            inDegree[pre[0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            ans.add(u);
            if (graph[u] == null) {
                continue;
            }
            for (int v : graph[u]) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
        return ans.size() == numCourses ? ans.stream().mapToInt(i -> i).toArray() : new int[]{};
    }

    public static void main(String[] args) {
        var solution = new CourseScheduleII();
        System.out.println(solution.findOrder(2, new int[][]{{1, 0}}).length);
    }

}
