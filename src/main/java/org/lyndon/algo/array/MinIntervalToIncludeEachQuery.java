package org.lyndon.algo.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC1851
 */
public class MinIntervalToIncludeEachQuery {

    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = intervals.length;
        int m = queries.length;
        int[][] qs = new int[m][2];
        for (int i = 0; i < m; i++) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }
        Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));

        // [interval_length, right_end]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int i = 0;
        int[] ans = new int[m];
        for (int[] q : qs) {
            while (i < n && intervals[i][0] <= q[0]) {
                pq.offer(new int[] {intervals[i][1] - intervals[i][0] + 1, intervals[i][1]});
                i++;
            }
            while (!pq.isEmpty() && pq.peek()[1] < q[0]) {
                pq.poll();
            }
            ans[q[1]] = pq.isEmpty() ? -1 : pq.peek()[0];
        }
        return ans;
    }

}
