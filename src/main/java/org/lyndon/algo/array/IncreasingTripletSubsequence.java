package org.lyndon.algo.array;

/**
 * LC334
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 * <p>
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 * <p>
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: One of the valid triplet is (1, 4, 5), because nums[1] == 1 < nums[4] == 4 < nums[5] == 6.
 */
public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) {
                min = num;
            } else if (num <= secondMin) {
                secondMin = num;
            } else {
                return true;
            }
        }
        return false;
    }

}
