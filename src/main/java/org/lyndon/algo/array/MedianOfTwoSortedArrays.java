package org.lyndon.algo.array;

/**
 * LC4
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int half = (total + 1) / 2;

        int low = 0;
        int high = m;
        while (low <= high) {
            int i = (low + high) / 2;
            int j = half - i;

            int L1 = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int R1 = i == m ? Integer.MAX_VALUE : nums1[i];
            int L2 = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int R2 = j == n ? Integer.MAX_VALUE : nums2[j];
            if (L1 <= R2 && L2 <= R1) {
                if (total % 2 == 1) {
                    return Math.max(L1, L2);
                }
                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
            } else if (L1 > R2) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        return 0;
    }

}
