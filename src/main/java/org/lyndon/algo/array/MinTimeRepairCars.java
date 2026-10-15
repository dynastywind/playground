package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC2594
 */
public class MinTimeRepairCars {

    public long repairCars(int[] ranks, int cars) {
        int m = ranks.length;
        Arrays.sort(ranks);
        long lo = ranks[0];
        long hi = (long) ranks[m - 1] * cars * cars;
        while (lo < hi) {
            long mid = (lo + hi) >> 1;
            if (check(ranks, cars, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean check(int[] ranks, int cars, long time) {
        int rem = cars;
        for (int i = ranks.length - 1; i >= 0; i--) {
            int n = (int) Math.sqrt((double) time / ranks[i]);
            rem -= n;
            if (rem <= 0) {
                return true;
            }
        }
        return rem <= 0;
    }

    static void main() {
        var solution = new MinTimeRepairCars();
        System.out.println(solution.repairCars(new int[]{4, 2, 3, 1}, 10));
        System.out.println(solution.repairCars(new int[]{5, 1, 8}, 6));
    }

}
