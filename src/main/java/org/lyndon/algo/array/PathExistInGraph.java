package org.lyndon.algo.array;

/**
 * LC3532
 * You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
 * <p>
 * You are also given an integer array nums of length n sorted in non-decreasing order, and an integer maxDiff.
 * <p>
 * An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
 * <p>
 * You are also given a 2D integer array queries. For each queries[i] = [ui, vi], determine whether there exists a path between nodes ui and vi.
 * <p>
 * Return a boolean array answer, where answer[i] is true if there exists a path between ui and vi in the ith query and false otherwise.
 */
public class PathExistInGraph {

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] comp = new int[n];
        for (int i = 1; i < n; i++) {
            comp[i] = comp[i - 1] + (nums[i] - nums[i - 1] <= maxDiff ? 0 : 1);
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = comp[queries[i][0]] == comp[queries[i][1]];
        }
        return ans;
    }

}
