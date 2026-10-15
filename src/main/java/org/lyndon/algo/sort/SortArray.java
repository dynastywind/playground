package org.lyndon.algo.sort;

import org.lyndon.utils.PrintUtils;

import java.util.Random;

/**
 * LC912
 * Given an array of integers nums, sort the array in ascending order and return it.
 * <p>
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 */
public class SortArray {

    public int[] sortArray(int[] nums) {
        sort(nums, 0, nums.length - 1, new Random());
        return nums;
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
                swap(nums, i, lt);
                lt++;
                i++;
            } else if (nums[i] > pivot) {
                swap(nums, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        sort(nums, low, lt - 1, random);
        sort(nums, gt + 1, high, random);
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        var solution = new SortArray();
        PrintUtils.printArray(solution.sortArray(new int[]{-4, 0, 7, 4, 9, -5, -1, 0, -7, -1}));
    }

}
