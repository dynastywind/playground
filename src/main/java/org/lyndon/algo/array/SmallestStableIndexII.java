package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC3904
 */
public class SmallestStableIndexII {

    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] max = new int[n + 1];
        int[] min = new int[n + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            max[i] = Math.max(max[i - 1], nums[i - 1]);
            min[n - i] = Math.min(min[n - i + 1], nums[n - i]);
        }
        for (int i = 0; i < n; i++) {
            int diff = max[i + 1] - min[i];
            if (diff <= k) {
                return i;
            }
        }
        return -1;
    }

    static void main() {
        var solution = new SmallestStableIndexII();
        System.out.println(solution.firstStableIndex(new int[]{5, 0, 1, 4}, 3));
        System.out.println(solution.firstStableIndex(new int[]{3, 2, 1}, 1));
        System.out.println(solution.firstStableIndex(new int[]{0}, 0));
        System.out.println(solution.firstStableIndex(new int[]{2, 0, 2}, 3));
    }

}
