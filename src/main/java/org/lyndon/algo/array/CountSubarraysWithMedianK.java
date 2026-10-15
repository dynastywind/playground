package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC2488
 * You are given an array nums of size n consisting of distinct integers from 1 to n and a positive integer k.
 * <p>
 * Return the number of non-empty subarrays in nums that have a median equal to k.
 * <p>
 * Note:
 * <p>
 * The median of an array is the middle element after sorting the array in ascending order. If the array is of even length, the median is the left middle element.
 * For example, the median of [2,3,1,4] is 2, and the median of [8,4,3,5,1] is 4.
 * A subarray is a contiguous part of an array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,4,5], k = 4
 * Output: 3
 * Explanation: The subarrays that have a median equal to 4 are: [4], [4,5] and [1,4,5].
 * Example 2:
 * <p>
 * Input: nums = [2,3,1], k = 3
 * Output: 1
 * Explanation: [3] is the only subarray that has a median equal to 3.
 */
public class CountSubarraysWithMedianK {

    public int countSubarrays(int[] nums, int k) {
        int pos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                pos = i;
                break;
            }
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        int d = 0;
        for (int i = pos - 1; i >= 0; i--) {
            if (nums[i] > k) {
                d += 1;
            } else {
                d -= 1;
            }
            cnt.merge(d, 1, Integer::sum);
        }

        int ans = 0;
        d = 0;
        for (int i = pos; i < nums.length; i++) {
            if (nums[i] > k) {
                d += 1;
            } else if (nums[i] < k) {
                d -= 1;
            }
            ans += cnt.getOrDefault(-d, 0) + cnt.getOrDefault(1 - d, 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new CountSubarraysWithMedianK();
        System.out.println(solution.countSubarrays(new int[]{3, 2, 1, 4, 5}, 4));
        System.out.println(solution.countSubarrays(new int[]{2, 3, 1}, 3));
    }

}
