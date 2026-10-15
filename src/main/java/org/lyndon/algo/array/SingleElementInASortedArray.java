package org.lyndon.algo.array;

/**
 * LC540
 */
public class SingleElementInASortedArray {

    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            }
            if (nums[mid] == nums[mid - 1]) {
                if ((mid - lo) % 2 == 0) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if ((hi - mid) % 2 == 0) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return nums[lo];
    }

}
