package org.lyndon.algo.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC857
 */
public class MinCostToHireKWorkers {

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double[] ratios = new double[n];
        Integer[] pos = new Integer[n];
        for (int i = 0; i < n; i++) {
            ratios[i] = (double) wage[i] / quality[i];
            pos[i] = i;
        }
        Arrays.sort(pos, (a, b) -> Double.compare(ratios[a], ratios[b]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int totalQuality = 0;
        double ans = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            pq.offer(quality[pos[i]]);
            totalQuality += quality[pos[i]];
            if (pq.size() > k) {
                totalQuality -= pq.poll();
            }
            if (pq.size() == k) {
                ans = Math.min(ans, totalQuality * ratios[pos[i]]);
            }
        }
        return ans;
    }

}
