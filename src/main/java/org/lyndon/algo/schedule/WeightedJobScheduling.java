package org.lyndon.algo.schedule;

import java.util.Arrays;

/**
 * LC1235
 */
public class WeightedJobScheduling {

    public int jobScheduling(int[] s, int[] e, int[] p) {
        int n = s.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(e[a], e[b]));

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = e[order[i]];
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int j = order[i - 1];
            int k = upper(next, i - 1, s[j]);
            dp[i] = Math.max(dp[i - 1], dp[k] + p[j]);
        }
        return dp[n];
    }

    private int upper(int[] arr, int hi, int x) {
        int lo = 0;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (arr[mid] <= x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
