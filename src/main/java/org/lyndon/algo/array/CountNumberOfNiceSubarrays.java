package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC1248
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 * <p>
 * Return the number of nice sub-arrays.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 * <p>
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There are no odd numbers in the array.
 * Example 3:
 * <p>
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 */
public class CountNumberOfNiceSubarrays {

    // 0,0,0,1,0,0,1,0,0,0
    // 0,0,0,0,1,1,1,2,2,2,2
    public int numberOfSubarrays(int[] nums, int k) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + (nums[i - 1] % 2 == 1 ? 1 : 0);
        }
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < prefix.length; i++) {
            if (map.containsKey(prefix[i] - k)) {
                cnt += map.get(prefix[i] - k);
            }
            map.merge(prefix[i], 1, Integer::sum);
        }
        return cnt;
    }

}
