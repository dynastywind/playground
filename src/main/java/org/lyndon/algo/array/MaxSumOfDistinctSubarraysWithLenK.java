package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC2461
 */
public class MaxSumOfDistinctSubarraysWithLenK {

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }

        int i = 0;
        long ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (i < n) {
            map.merge(nums[i], 1, Integer::sum);
            if (i >= k - 1) {
                if (map.size() == k) {
                    ans = Math.max(ans, pre[i + 1] - pre[i - k + 1]);
                }
                if (map.merge(nums[i - k + 1], -1, Integer::sum) == 0) {
                    map.remove(nums[i - k + 1]);
                }
            }
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new MaxSumOfDistinctSubarraysWithLenK();
        System.out.println(solution.maximumSubarraySum(new int[]{1, 5, 4, 2, 9, 9, 9}, 3));
        System.out.println(solution.maximumSubarraySum(new int[]{4, 4, 4}, 3));
    }

}
