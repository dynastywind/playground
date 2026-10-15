package org.lyndon.algo.array;

/**
 * LC2552
 * Given a 0-indexed integer array nums of size n containing all numbers from 1 to n, return the number of increasing quadruplets.
 * <p>
 * A quadruplet (i, j, k, l) is increasing if:
 * <p>
 * 0 <= i < j < k < l < n, and
 * nums[i] < nums[k] < nums[j] < nums[l].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,2,4,5]
 * Output: 2
 * Explanation:
 * - When i = 0, j = 1, k = 2, and l = 3, nums[i] < nums[k] < nums[j] < nums[l].
 * - When i = 0, j = 1, k = 2, and l = 4, nums[i] < nums[k] < nums[j] < nums[l].
 * There are no other quadruplets, so we return 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Explanation: There exists only one quadruplet with i = 0, j = 1, k = 2, l = 3, but since nums[j] < nums[k], we return 0.
 */
public class CountIncreasingQuadruplets {

    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        // i's left count of less than x
        int[][] less = new int[n][n + 1];
        for (int i = 1; i < n; i++) {
            for (int x = 1; x <= n; x++) {
                less[i][x] = less[i - 1][x] + (nums[i - 1] < x ? 1 : 0);
            }
        }
        // i's right count of more than x
        int[][] greater = new int[n][n + 1];
        for (int i = n - 2; i >= 0; i--) {
            for (int x = 1; x <= n; x++) {
                greater[i][x] = greater[i + 1][x] + (nums[i + 1] > x ? 1 : 0);
            }
        }
        long ans = 0;
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (nums[k] >= nums[j]) {
                    continue;
                }
                ans += (long) less[j][nums[k]] * greater[k][nums[j]];
            }
        }
        return ans;
    }

}
