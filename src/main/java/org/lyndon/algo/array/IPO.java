package org.lyndon.algo.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LC 502
 */
public class IPO {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i] = new int[]{profits[i], capital[i]};
        }
        Arrays.sort(projects, (a, b) -> Integer.compare(a[1], b[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        int ans = w;
        for (int i = 0; i < k; i++) {
            while (j < n && projects[j][1] <= ans) {
                pq.offer(projects[j++][0]);
            }
            if (pq.isEmpty()) {
                break;
            }
            ans += pq.poll();
        }
        return ans;
    }

}
