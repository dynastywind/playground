package org.lyndon.algo.array;

import java.util.HashSet;
import java.util.Set;

/**
 * LC3719
 * A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the number of distinct odd numbers.
 * <p>
 * Return the length of the longest balanced subarray.
 */
public class LongestBalancedSubarray {

    public int longestBalanced(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            int odd = 0;
            int even = 0;
            for (int j = i; j < n; j++) {
                if (seen.add(nums[j])) {
                    if (nums[j] % 2 == 0) {
                        even++;
                    } else {
                        odd++;
                    }
                }
                if (even == odd) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

}
