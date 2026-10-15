package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC695
 */
public class MaxErasureValue {

    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        int left = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (cnt.merge(nums[i], 1, Integer::sum) == 1) {
                ans = Math.max(ans, pre[i + 1] - pre[left]);
            }
            while (cnt.getOrDefault(nums[i], 0) > 1) {
                if (cnt.merge(nums[left], -1, Integer::sum) == 0) {
                    cnt.remove(nums[left]);
                }
                left++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new MaxErasureValue();
        System.out.println(solution.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6}));
        System.out.println(solution.maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5}));
    }

}
