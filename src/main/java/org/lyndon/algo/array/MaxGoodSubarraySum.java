package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC3026
 */
public class MaxGoodSubarraySum {

    public long maximumSubarraySum(int[] nums, int k) {
        long ans = Long.MIN_VALUE;
        long pre = 0;
        Map<Integer, Long> minPre = new HashMap<>();
        for (int num : nums) {
            // min pre when num is at the left
            minPre.merge(num, pre, Math::min);
            pre += num;

            if (minPre.containsKey(num + k)) {
                ans = Math.max(ans, pre - minPre.get(num + k));
            }
            if (minPre.containsKey(num - k)) {
                ans = Math.max(ans, pre - minPre.get(num - k));
            }
        }
        return ans == Long.MIN_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        var solution = new MaxGoodSubarraySum();
        System.out.println(solution.maximumSubarraySum(new int[]{1, 5}, 2));
    }

}
