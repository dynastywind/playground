package org.lyndon.algo.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LC3020
 */
public class FindMaxNumOfElementsInSubset {

    public int maximumLength(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        Arrays.sort(nums);
        for (int num : nums) {
            cnt.merge((long) num, 1, Integer::sum);
        }
        int ones = cnt.getOrDefault(1L, 0);
        int ans = ones % 2 == 1 ? ones : Math.max(ones - 1, 1);
        int max = nums[nums.length - 1];
        int cur = 0;
        for (int num : nums) {
            for (long j = num; j <= max; j *= j) {
                if (!cnt.containsKey(j)) {
                    break;
                }
                int c = cnt.remove(j);
                if (c == 1) {
                    cur++;
                    break;
                }
                cur += 2;
            }
            if (cur > 2) {
                if (cur % 2 == 0) {
                    ans = Math.max(ans, cur - 1);
                } else {
                    ans = Math.max(ans, cur);
                }
            }
            cur = 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new FindMaxNumOfElementsInSubset();
        System.out.println(solution.maximumLength(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024}));
    }

}
