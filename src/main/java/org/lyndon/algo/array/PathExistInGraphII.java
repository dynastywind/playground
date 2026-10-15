package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC3534
 * You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
 * <p>
 * You are also given an integer array nums of length n and an integer maxDiff.
 * <p>
 * An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
 * <p>
 * You are also given a 2D integer array queries. For each queries[i] = [ui, vi], find the minimum distance between nodes ui and vi. If no path exists between the two nodes, return -1 for that query.
 * <p>
 * Return an array answer, where answer[i] is the result of the ith query.
 * <p>
 * Note: The edges between the nodes are unweighted.
 */
public class PathExistInGraphII {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] ordered = new Integer[n];
        for (int i = 0; i < n; i++) {
            ordered[i] = i;
        }
        Arrays.sort(ordered, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] a = new int[n];
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nums[ordered[i]];
            pos[ordered[i]] = i;
        }

        int[] comp = new int[n];
        for (int i = 1; i < n; i++) {
            comp[i] = comp[i - 1] + (a[i] - a[i - 1] > maxDiff ? 1 : 0);
        }

        int K = 1;
        while ((1 << K) < n) {
            K++;
        }
        // binary lifting
        // location from i after jumping 2^K times
        int[][] up = new int[K][n];
        for (int i = 0, j = 0; i < n; i++) {
            // i is isolated
            if (j < i) {
                j = i;
            }
            while (j + 1 < n && a[j + 1] - a[i] <= maxDiff) {
                j++;
            }
            up[0][i] = j;
        }
        for (int k = 1; k < K; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            if (u == v) {
                ans[i] = 0;
                continue;
            }
            int low = pos[u];
            int high = pos[v];
            if (low > high) {
                int tmp = low;
                low = high;
                high = tmp;
            }
            // not connected
            if (comp[low] != comp[high]) {
                ans[i] = -1;
                continue;
            }

            int cur = low;
            int steps = 0;
            for (int k = K - 1; k >= 0; k--) {
                if (up[k][cur] < high) {
                    cur = up[k][cur];
                    steps += (1 << k);
                }
            }
            // +1 to jump over high
            ans[i] = steps + 1;
        }
        return ans;
    }

}
