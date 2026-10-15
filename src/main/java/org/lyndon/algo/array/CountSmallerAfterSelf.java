package org.lyndon.algo.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LC315
 * <p>
 * Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 * <p>
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: nums = [-1,-1]
 * Output: [0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class CountSmallerAfterSelf {

    public List<Integer> countSmaller(int[] nums) {
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        int[] counts = new int[nums.length];
        int[] tmp = new int[nums.length];
        mergeSort(nums, index, counts, tmp, 0, nums.length - 1);
        return Arrays.stream(counts)
                .boxed()
                .collect(Collectors.toList());
    }

    private void mergeSort(int[] nums, int[] index, int[] counts, int[] tmp, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) >>> 1;
        mergeSort(nums, index, counts, tmp, low, mid);
        mergeSort(nums, index, counts, tmp, mid + 1, high);
        merge(nums, index, counts, tmp, low, high, mid);
    }

    private void merge(int[] nums, int[] index, int[] counts, int[] tmp, int low, int high, int mid) {
        int i = low;
        int j = mid + 1;
        int k = low;
        int rightCount = 0;
        while (i <= mid && j <= high) {
            if (nums[index[i]] <= nums[index[j]]) {
                counts[index[i]] += rightCount;
                tmp[k++] = index[i++];
            } else {
                rightCount++;
                tmp[k++] = index[j++];
            }
        }
        while (i <= mid) {
            counts[index[i]] += rightCount;
            tmp[k++] = index[i++];
        }
        while (j <= high) {
            tmp[k++] = index[j++];
        }
        System.arraycopy(tmp, low, index, low, high - low + 1);
    }

}
