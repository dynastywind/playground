package org.lyndon.algo.array;

/**
 * LC644
 */
public class MaxAvgSubarrayII {

    public double findMaxAverage(int[] nums, int k) {
        double lo = Double.MAX_VALUE;
        double hi = Double.MIN_VALUE;
        for (int num : nums) {
            lo = Math.min(lo, num);
            hi = Math.max(hi, num);
        }
        while (hi - lo > 1e-5) {
            double mid = (lo + hi) / 2;
            if (check(nums, k, mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private boolean check(int[] nums, int k, double avg) {
        double sum = 0;
        double pre = 0;
        double min = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i] - avg;
        }
        if (sum >= 0) {
            return true;
        }
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - avg;
            pre += nums[i - k] - avg;
            min = Math.min(min, pre);
            if (sum - min >= 0) {
                return true;
            }
        }
        return false;
    }

}
