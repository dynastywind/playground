package org.lyndon.algo.array;

/**
 * LC1760
 */
public class MinLimitOfBallsInABag {

    public int minimumSize(int[] nums, int maxOperations) {
        int lo = 1;
        int hi = 0;
        for (int num : nums) {
            hi = Math.max(hi, num);
        }
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (check(nums, maxOperations, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean check(int[] nums, int maxOperations, int penalty) {
        int ops = 0;
        for (int num : nums) {
            if (num <= penalty) {
                continue;
            }
            ops += (num + penalty - 1) / penalty - 1;
            if (ops > maxOperations) {
                return false;
            }
        }
        return true;
    }

}
