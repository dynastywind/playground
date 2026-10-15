package org.lyndon.algo.meeting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LC2050
 */
public class ParallelCoursesIII {

    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] degrees = new int[n];
        for (int[] relation : relations) {
            graph[relation[0] - 1].add(relation[1] - 1);
            degrees[relation[1] - 1]++;
        }

        int[] dp = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 0) {
                queue.offer(i);
                dp[i] = time[i];
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                for (int v : graph[u]) {
                    degrees[v]--;
                    dp[v] = Math.max(dp[v], dp[u] + time[v]);
                    if (degrees[v] == 0) {
                        queue.offer(v);
                    }
                }
            }
        }

        int max = 0;
        for (int t : dp) {
            max = Math.max(max, t);
        }
        return max;
    }

    public static void main(String[] args) {
        var parallelCoursesIII = new ParallelCoursesIII();
        System.out.println(parallelCoursesIII.minimumTime(3, new int[][]{{1, 3}, {2, 3}}, new int[]{3, 2, 5}));
        System.out.println(parallelCoursesIII.minimumTime(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}}, new int[]{1, 2, 3, 4, 5}));
    }

}
