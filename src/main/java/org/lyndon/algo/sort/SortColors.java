package org.lyndon.algo.sort;

import java.util.Random;

/**
 * LC75
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * You must solve this problem without using the library's sort function.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 */
public class SortColors {

    public void sortColors(int[] nums) {
        sort(nums, 0, nums.length - 1, new Random());
    }

    private void sort(int[] nums, int low, int high, Random random) {
        if (low >= high) {
            return;
        }
        int pivot = nums[low + random.nextInt(high - low + 1)];
        int lt = low;
        int gt = high;
        int i = low;
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, i++, lt++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }
        sort(nums, low, lt - 1, random);
        sort(nums, gt + 1, high, random);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
