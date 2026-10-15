package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC740
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
 * <p>
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * Example 2:
 * <p>
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 */
public class DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        long prev = 0, prev2 = 0;
        int prevV = Integer.MIN_VALUE;
        while (i < n) {
            int v = nums[i];
            long s = 0;
            while (i < n && nums[i] == v) {
                s += nums[i++];
            }

            if (v == prevV + 1) {
                long cur = Math.max(prev, prev2 + s);
                prev2 = prev;
                prev = cur;
            } else {
                prev2 = prev;
                prev += s;
            }
            prevV = v;
        }
        return (int) prev;
    }

}
