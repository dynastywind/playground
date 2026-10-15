package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * LC442
 */
public class FindAllDupInArray {

    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] - 1 != i && nums[i] != nums[nums[i] - 1]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        PrintUtils.printArray(nums);
        List<Integer> dups = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                dups.add(nums[i]);
            }
        }
        return dups;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        var solution = new FindAllDupInArray();
        /*
            4 3 2 7 8 2 3 1
            7 3 2 4 8 2 3 1
            3 3 2 4 8 2 7 1
            2 3 3 4 8 2 7 1
            3 2 3 4 8 2 7 1
         */
        PrintUtils.printList(solution.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

}
