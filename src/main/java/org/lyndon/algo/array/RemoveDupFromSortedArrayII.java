package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

/**
 * LC80
 */
public class RemoveDupFromSortedArrayII {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        int removed = 0;
        while (i < n - removed) {
            int cur = nums[i];
            int j = i;
            int dup = 2;
            while (j < n - removed && nums[j] == cur) {
                j++;
                dup--;
            }
            if (dup < 0) {
                shift(nums, j + dup, -dup, n - removed);
                removed -= dup;
            }
            i = j + Math.min(0, dup);
        }
        return n - removed;
    }

    private void shift(int[] nums, int start, int len, int limit) {
        int i = start;
        while (i + len < limit) {
            nums[i] = nums[i + len];
            i++;
        }
    }

    public static void main(String[] args) {
        var solution = new RemoveDupFromSortedArrayII();
        var arr = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(solution.removeDuplicates(arr));
        PrintUtils.printArray(arr);
        arr = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(solution.removeDuplicates(arr));
        PrintUtils.printArray(arr);
        arr = new int[]{0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3};
        System.out.println(solution.removeDuplicates(arr));
        PrintUtils.printArray(arr);
    }

}
