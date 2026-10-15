package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

/**
 * LC189
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if (k == 0) {
            return;
        }
        int cnt = 0;
        for (int start = 0; cnt < n; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = to(current, k, n);
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                current = next;
                cnt++;
            } while (start != current);
        }
    }

    private int to(int from, int k, int n) {
        return (from + k) % n;
    }

    public static void main(String[] args) {
        var solution = new RotateArray();
        // 123416
        // 125416
        // 325416
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        solution.rotate(arr, 4);
        PrintUtils.printArray(arr);
    }

}
