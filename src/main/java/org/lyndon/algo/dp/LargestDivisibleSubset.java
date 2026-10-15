package org.lyndon.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC368
 * Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 * <p>
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,2]
 * Explanation: [1,3] is also accepted.
 * Example 2:
 * <p>
 * Input: nums = [1,2,4,8]
 * Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        Arrays.sort(nums);
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = maxIndex; i >= 0; i = prev[i]) {
            result.addLast(nums[i]);
        }
        return result;
    }

}
