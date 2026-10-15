package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC611
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 * <p>
 * Input: nums = [4,2,3,4]
 * Output: 4
 */
public class ValidTriangleNumber {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int k = nums.length - 1; k >= 2; k--) {
            if (nums[k] == 0) {
                break;
            }
            int i = 0;
            int j = k - 1;
            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    ans += j - i;
                    j--;
                } else {
                    i++;
                }
            }
        }
        return ans;
    }

}
