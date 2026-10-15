package org.lyndon.algo.array;

/**
 * LC3355
 */
public class ZeroArrayTransformI {

    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int[] query : queries) {
            diff[query[0]]++;
            diff[query[1] + 1]--;
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
