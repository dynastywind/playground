package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC1749
 */
public class MaxAbsSumOfSubarray {

    public int maxAbsoluteSum(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        int min = Integer.MAX_VALUE;
        int curMax = 0;
        int curMin = Integer.MAX_VALUE;
        for (int num : nums) {
            curMax += num;
            if (curMax < 0) {
                curMax = 0;
            }
            curMin += num;
            if (curMin > 0) {
                curMin = 0;
            }
            max = Math.max(max, curMax);
            min = Math.min(min, curMin);
        }
        return Math.max(max, -min);
    }

}
