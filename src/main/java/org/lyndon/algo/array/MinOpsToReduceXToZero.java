package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC1658
 */
public class MinOpsToReduceXToZero {

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int target = total - x;
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return n;
        }

        int best = -1;
        int sum = 0;
        int left = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum > target && left <= i) {
                sum -= nums[left++];
            }
            if (sum == target) {
                best = Math.max(best, i - left + 1);
            }
        }
        return best < 0 ? -1 : n - best;
    }

    static void main() {
        var solution = new MinOpsToReduceXToZero();
        System.out.println(solution.minOperations(new int[]{1, 1, 4, 2, 3}, 5));
        System.out.println(solution.minOperations(new int[]{5, 6, 7, 8, 9}, 4));
        System.out.println(solution.minOperations(new int[]{3, 2, 20, 1, 1, 3}, 10));
        System.out.println(solution.minOperations(new int[]{5}, 5));
    }

}
