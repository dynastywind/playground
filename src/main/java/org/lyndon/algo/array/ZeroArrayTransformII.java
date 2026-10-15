package org.lyndon.algo.array;

/**
 * LC3356
 */
public class ZeroArrayTransformII {

    public int minZeroArray(int[] nums, int[][] queries) {
        int lo = 0;
        int hi = queries.length;
        if (!check(nums, queries, hi)) {
            return -1;
        }
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (check(nums, queries, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean check(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int i = 0; i < k; i++) {
            int[] q = queries[i];
            diff[q[0]] += q[2];
            diff[q[1] + 1] -= q[2];
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum < nums[i]) {
                return false;
            }
        }
        return true;
    }

}
