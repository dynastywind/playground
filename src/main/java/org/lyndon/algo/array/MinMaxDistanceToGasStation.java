package org.lyndon.algo.array;

/**
 * LC774
 * You are given an integer array stations that represents the positions of the gas stations on the x-axis. You are also given an integer k.
 * <p>
 * You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on an integer position.
 * <p>
 * Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.
 * <p>
 * Return the smallest possible value of penalty(). Answers within 10-6 of the actual answer will be accepted.
 * <p>
 * Example 1:
 * <p>
 * Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
 * Output: 0.50000
 * Example 2:
 * <p>
 * Input: stations = [23,24,36,39,46,56,57,65,84,98], k = 1
 * Output: 14.00000
 */
public class MinMaxDistanceToGasStation {

    public double minmaxGasDist(int[] stations, int k) {
        double lo = 0;
        double hi = 0;
        for (int i = 1; i < stations.length; i++) {
            hi = Math.max(hi, stations[i] - stations[i - 1]);
        }

        while (hi - lo > 1e-6) {
            double mid = (lo + hi) / 2;
            if (check(stations, k, mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return lo;
    }

    private boolean check(int[] stations, int k, double d) {
        long cnt = 0;
        for (int i = 1; i < stations.length; i++) {
            cnt += (long) (stations[i] - stations[i - 1]) / d;
            if (cnt > k) {
                return false;
            }
        }
        return true;
    }

}
