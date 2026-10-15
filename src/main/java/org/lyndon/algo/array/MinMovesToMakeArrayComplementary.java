package org.lyndon.algo.array;

/**
 * LC1674
 */
public class MinMovesToMakeArrayComplementary {

    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[2 * limit + 2];
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            int lo = Math.min(a, b);
            int hi = Math.max(a, b);
            int s = a + b;

            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

            diff[lo + 1]--;
            diff[hi + limit + 1]++;

            diff[s]--;
            diff[s + 1]++;
        }

        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 2; i <= 2 * limit; i++) {
            sum += diff[i];
            ans = Math.min(ans, sum);
        }
        return ans;
    }

}
