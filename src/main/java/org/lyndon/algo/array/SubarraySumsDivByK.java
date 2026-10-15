package org.lyndon.algo.array;

/**
 * LC974
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * Example 2:
 * <p>
 * Input: nums = [5], k = 9
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * 2 <= k <= 104
 */
public class SubarraySumsDivByK {

    /*
        (prefix[j] - prefix[i]) mod k = 0
        prefix[j] = prefix[i] (mod k)
     */
    public int subarraysDivByK(int[] nums, int k) {
        int ans = 0;
        int prefix = 0;
        int[] cnt = new int[k];
        cnt[0] = 1;
        for (int num : nums) {
            prefix = ((prefix + num) % k + k) % k;
            ans += cnt[prefix];
            cnt[prefix]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new SubarraySumsDivByK();
        System.out.println(solution.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }

}
