package org.lyndon.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * LC2035
 * You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.
 * <p>
 * Return the minimum possible absolute difference.
 */
public class PartitionArrayIntoTwoArraysToMinSumDiff {

    /*
        |x - (sum - x)| = |sum - 2x| -> 0
        sum -> 2x
     */
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        List<List<Integer>> lefts = subsetSums(Arrays.copyOfRange(nums, 0, n));
        List<List<Integer>> rights = subsetSums(Arrays.copyOfRange(nums, n, 2 * n));

        List<TreeSet<Integer>> rightSets = new ArrayList<>();
        for (List<Integer> right : rights) {
            rightSets.add(new TreeSet<>(right));
        }

        int ans = Integer.MAX_VALUE;
        for (int k = 0; k <= n; k++) {
            TreeSet<Integer> set = rightSets.get(n - k);
            for (int a : lefts.get(k)) {
                int target = (sum - 2 * a) / 2;
                Integer b1 = set.floor(target);
                Integer b2 = set.ceiling(target);
                if (b1 != null) {
                    ans = Math.min(ans, Math.abs(sum - 2 * (a + b1)));
                }
                if (b2 != null) {
                    ans = Math.min(ans, Math.abs(sum - 2 * (a + b2)));
                }
            }
        }
        return ans;
    }

    private List<List<Integer>> subsetSums(int[] arr) {
        int n = arr.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            result.add(new ArrayList<>());
        }
        final int full = 1 << n;
        for (int mask = 0; mask < full; mask++) {
            int sum = 0;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if ((mask >> i & 1) == 1) {
                    sum += arr[i];
                    cnt++;
                }
            }
            result.get(cnt).add(sum);
        }
        return result;
    }

}
