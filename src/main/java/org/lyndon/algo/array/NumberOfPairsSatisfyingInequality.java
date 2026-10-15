package org.lyndon.algo.array;

/**
 * LC2426
 * You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff. Find the number of pairs (i, j) such that:
 * <p>
 * 0 <= i < j <= n - 1 and
 * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
 * Return the number of pairs that satisfy the conditions.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
 * Output: 3
 * Explanation:
 * There are 3 pairs that satisfy the conditions:
 * 1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair satisfies the conditions.
 * 2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair satisfies the conditions.
 * 3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair satisfies the conditions.
 * Therefore, we return 3.
 * Example 2:
 * <p>
 * Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
 * Output: 0
 * Explanation:
 * Since there does not exist any pair that satisfies the conditions, we return 0.
 */
public class NumberOfPairsSatisfyingInequality {

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int[] a = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            a[i] = nums1[i] - nums2[i];
        }
        return merge(a, new int[nums1.length], 0, nums1.length - 1, diff);
    }

    private long merge(int[] arr, int[] tmp, int low, int high, int diff) {
        if (low >= high) {
            return 0;
        }
        int mid = (low + high) >>> 1;
        long cnt = merge(arr, tmp, low, mid, diff) + merge(arr, tmp, mid + 1, high, diff);

        for (int i = low, j = mid + 1; j <= high; j++) {
            while (i <= mid && arr[i] - arr[j] <= diff) {
                i++;
            }
            cnt += i - low;
        }

        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= high) {
            tmp[k++] = arr[j++];
        }
        System.arraycopy(tmp, low, arr, low, high - low + 1);
        return cnt;
    }

}
