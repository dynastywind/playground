package org.lyndon.algo.array;

/**
 * LC154
 */
public class FindMinInRotatedSortedArrayII {

    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] == nums[hi]) {
                hi--;
                continue;
            }
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        var solution = new FindMinInRotatedSortedArrayII();
        System.out.println(solution.findMin(new int[]{1, 3, 5}));
        System.out.println(solution.findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println(solution.findMin(new int[]{3, 1, 2}));
        System.out.println(solution.findMin(new int[]{1, 2, 3}));
    }

}
