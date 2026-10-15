package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC1478
 * Given the array houses where houses[i] is the location of the ith house along a street and an integer k, allocate k mailboxes in the street.
 * <p>
 * Return the minimum total distance between each house and its nearest mailbox.
 * <p>
 * Input: houses = [1,4,8,10,20], k = 3
 * Output: 5
 * Explanation: Allocate mailboxes in position 3, 9 and 20.
 * Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
 * <p>
 * Input: houses = [2,3,5,12,18], k = 2
 * Output: 9
 * Explanation: Allocate mailboxes in position 3 and 14.
 * Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
 */
public class AllocateMailboxes {

    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int[][] cost = new int[houses.length][houses.length];
        for (int len = 2; len <= houses.length; len++) {
            for (int i = 0; i + len <= houses.length; i++) {
                int j = i + len - 1;
                cost[i][j] = cost[i + 1][j - 1] + houses[j] - houses[i];
            }
        }

        int[][] dp = new int[houses.length + 1][k + 1];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= houses.length; i++) {
            for (int t = 1; t <= Math.min(i, k); t++) {
                for (int j = t - 1; j < i; j++) {
                    dp[i][t] = Math.min(dp[i][t], dp[j][t - 1] + cost[j][i - 1]);
                }
            }
        }
        return dp[houses.length][k];
    }

}
