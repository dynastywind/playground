package org.lyndon.algo.array;

/**
 * LC34
 */
public class FirstLastPositionInArray {

    public int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = upperBound(nums, target) - 1;
        return new int[]{left, right};
    }

    private int lowerBound(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private int upperBound(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
