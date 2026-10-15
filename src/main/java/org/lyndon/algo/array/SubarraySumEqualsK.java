package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 560
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int j : prefix) {
            if (map.containsKey(j - k)) {
                ans += map.get(j - k);
            }
            map.merge(j, 1, Integer::sum);
        }
        return ans;
    }
}
