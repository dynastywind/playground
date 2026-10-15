package org.lyndon.algo.array;

/**
 * LC327
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.
 * <p>
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
 * Example 2:
 * <p>
 * Input: nums = [0], lower = 0, upper = 0
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * -105 <= lower <= upper <= 105
 * The answer is guaranteed to fit in a 32-bit
 */
public class CountOfRangeSum {

    /*
        lower <= p[b] - p[a] <= upper
        p[b] - upper <= p[a] <= p[b] - lower
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return mergeCnt(prefix, 0, nums.length, lower, upper, new long[nums.length + 1]);
    }

    private int mergeCnt(long[] prefix, int low, int high, int lower, int upper, long[] tmp) {
        if (low >= high) {
            return 0;
        }
        int mid = (low + high) >>> 1;
        int cnt = mergeCnt(prefix, low, mid, lower, upper, tmp) + mergeCnt(prefix, mid + 1, high, lower, upper, tmp);

        int left = mid + 1;
        int right = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (left <= high && prefix[left] - prefix[i] < lower) {
                left++;
            }
            while (right <= high && prefix[right] - prefix[i] <= upper) {
                right++;
            }
            cnt += right - left;
        }

        int l = low;
        int r = mid + 1;
        int k = low;
        while (l <= mid && r <= high) {
            if (prefix[l] < prefix[r]) {
                tmp[k++] = prefix[l++];
            } else {
                tmp[k++] = prefix[r++];
            }
        }
        while (l <= mid) {
            tmp[k++] = prefix[l++];
        }
        while (r <= high) {
            tmp[k++] = prefix[r++];
        }
        System.arraycopy(tmp, low, prefix, low, high - low + 1);
        return cnt;
    }

}
