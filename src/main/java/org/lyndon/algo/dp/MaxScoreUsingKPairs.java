package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC3836
 * You are given two integer arrays nums1 and nums2 of lengths n and m respectively, and an integer k.
 * <p>
 * You must choose exactly k pairs of indices (i1, j1), (i2, j2), ..., (ik, jk) such that:
 * <p>
 * 0 <= i1 < i2 < ... < ik < n
 * 0 <= j1 < j2 < ... < jk < m
 * For each chosen pair (i, j), you gain a score of nums1[i] * nums2[j].
 * <p>
 * The total score is the sum of the products of all selected pairs.
 * <p>
 * Return an integer representing the maximum achievable total score.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3,2], nums2 = [4,5,1], k = 2
 * <p>
 * Output: 22
 * <p>
 * Explanation:
 * <p>
 * One optimal choice of index pairs is:
 * <p>
 * (i1, j1) = (1, 0) which scores 3 * 4 = 12
 * (i2, j2) = (2, 1) which scores 2 * 5 = 10
 * This gives a total score of 12 + 10 = 22.
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [-2,0,5], nums2 = [-3,4,-1,2], k = 2
 * <p>
 * Output: 26
 * <p>
 * Explanation:
 * <p>
 * One optimal choice of index pairs is:
 * <p>
 * (i1, j1) = (0, 0) which scores -2 * -3 = 6
 * (i2, j2) = (2, 1) which scores 5 * 4 = 20
 * The total score is 6 + 20 = 26.
 * <p>
 * Example 3:
 * <p>
 * Input: nums1 = [-3,-2], nums2 = [1,2], k = 2
 * <p>
 * Output: -7
 * <p>
 * Explanation:
 * <p>
 * The optimal choice of index pairs is:
 * <p>
 * (i1, j1) = (0, 0) which scores -3 * 1 = -3
 * (i2, j2) = (1, 1) which scores -2 * 2 = -4
 * The total score is -3 + (-4) = -7
 */
public class MaxScoreUsingKPairs {

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        long[][][] dp = new long[m + 1][n + 1][k + 1];
        for (long[][] a : dp) {
            for (long[] b : a) {
                Arrays.fill(b, Long.MIN_VALUE / 4);
                b[0] = 0;
            }
        }
        // dp[i][j][k] = max(dp[i-1][j][k], dp[i][j-1][k], dp[i-1][j-1][k-1] + nums1[i] * nums2[j])
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int t = 1; t <= k; t++) {
                    dp[i][j][t] = Math.max(Math.max(dp[i - 1][j][t], dp[i][j - 1][t]), dp[i - 1][j - 1][t - 1] + (long) nums1[i - 1] * nums2[j - 1]);
                }
            }
        }
        return dp[m][n][k];
    }

    public static void main(String[] args) {
        var solution = new MaxScoreUsingKPairs();
        System.out.println(solution.maxScore(new int[]{-3, -2}, new int[]{1, 2}, 2));
    }

}
