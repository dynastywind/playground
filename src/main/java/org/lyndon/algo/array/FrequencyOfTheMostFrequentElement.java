package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC1838
 */
public class FrequencyOfTheMostFrequentElement {

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if ((long) nums[i] * (i - left + 1) - sum > k) {
                sum -= nums[left++];
            }
        }
        return nums.length - left;
    }

}
