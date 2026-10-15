package org.lyndon.algo.sort;

import java.util.Arrays;
import java.util.Random;

public class ThreeWayQuickSort {

    private static final Random RAND = new Random();

    public static void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = nums[low + RAND.nextInt(high - low + 1)];
        int lt = low, i = low, gt = high;
        // invariant: [low,lt) < pivot, [lt,i) == pivot, [i,gt] unexamined, (gt,high] > pivot
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, lt++, i++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }
        sort(nums, low, lt - 1);
        sort(nums, gt + 1, high);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] sample = {5, 2, 8, 5, 5, 1, 9, 5, 3, 5};
        sort(sample);
        System.out.println(Arrays.toString(sample));

        int n = 1_000_000;
        int[] random = RAND.ints(n, 0, 1000).toArray();
        int[] allEqual = new int[n];
        Arrays.fill(allEqual, 7);
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = i;
        }

        for (int[] arr : new int[][]{random, allEqual, sorted}) {
            int[] expected = arr.clone();
            Arrays.sort(expected);
            long start = System.nanoTime();
            sort(arr);
            long ms = (System.nanoTime() - start) / 1_000_000;
            System.out.println("sorted=" + Arrays.equals(arr, expected) + " in " + ms + " ms");
        }
    }

}
