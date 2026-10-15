package org.lyndon.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * LC3414
 */
public class MaxScoreOfNonOverlappingIntervals {

    private static class State {
        long weight;
        List<Integer> indices;
        State() {
            this.weight = 0;
            this.indices = new ArrayList<>();
        }
        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = upperBound(arr, arr[i][1], i + 1);
        }
        State[][] dp = new State[n + 1][5];
        for (int k = 0; k < 5; k++) {
            dp[n][k] = new State();
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k < 5; k++) {
                if (k == 0) {
                    dp[i][k] = new State();
                    continue;
                }

                State skip = dp[i + 1][k];
                State nextState = dp[next[i]][k - 1];
                long takeWeight = arr[i][2] + nextState.weight;
                List<Integer> takeIndices = new ArrayList<>(nextState.indices);
                takeIndices.add(arr[i][3]);
                Collections.sort(takeIndices);
                State take = new State(takeWeight, takeIndices);
                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> ans = dp[0][4].indices;
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private State better(State a, State b) {
        if (a.weight != b.weight) {
            return a.weight > b.weight ? a : b;
        }
        return compare(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compare(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());
        for (int i = 0; i < n; i++) {
            int ia = a.get(i);
            int ib = b.get(i);
            if (ia != ib) {
                return Integer.compare(ia, ib);
            }
        }
        return Integer.compare(a.size(), b.size());
    }

    private int upperBound(int[][] arr, int target, int left) {
        int lo = left;
        int hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (arr[mid][0] > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

}
