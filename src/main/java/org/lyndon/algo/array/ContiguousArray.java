package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC525
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Example 3:
 * <p>
 * Input: nums = [0,1,1,1,1,1,0,0,0]
 * Output: 6
 * Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum -= 1;
            } else {
                sum += 1;
            }
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new ContiguousArray();
        System.out.println(solution.findMaxLength(new int[]{0, 1}));
        System.out.println(solution.findMaxLength(new int[]{0, 1, 0}));
        System.out.println(solution.findMaxLength(new int[]{0, 1, 1, 1, 1, 1, 0, 0, 0}));
    }

}
