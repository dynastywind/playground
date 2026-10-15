package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC3738
 */
public class LongestNonDecreasingSubarrayAfterReplacingAtMostOneElement {

    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) {
                left[i] = left[i - 1] + 1;
                ans = Math.max(ans, left[i]);
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int a = i == 0 ? 0 : left[i - 1];
            int b = i == n - 1 ? 0 : right[i + 1];
            if (i > 0 && i < n - 1 && nums[i - 1] > nums[i + 1]) {
                ans = Math.max(ans, Math.max(a, b) + 1);
            } else {
                ans = Math.max(ans, a + b + 1);
            }
        }
        return ans;
    }

}
