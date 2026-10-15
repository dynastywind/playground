package org.lyndon.algo.dp.stone;

/**
 * LC1140
 * Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.
 * <p>
 * Alice and Bob take turns, with Alice starting first.
 * <p>
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). Initially, M = 1.
 * <p>
 * The game continues until all the stones have been taken.
 * <p>
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 * <p>
 * Example 1:
 * <p>
 * Input: piles = [2,7,9,4,4]
 * <p>
 * Output: 10
 * <p>
 * Explanation:
 * <p>
 * If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
 * If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.
 * So we return 10 since it's larger.
 * <p>
 * Example 2:
 * <p>
 * Input: piles = [1,2,3,4,5,100]
 * <p>
 * Output: 104
 */
public class StoneGameII {

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        // dp[i][m]: from pile i with M=m, the max stones player can get
        int[][] dp = new int[n][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {
                if (i + 2 * m >= n) {
                    dp[i][m] = suffix[i];
                    continue;
                }
                int best = 0;
                for (int x = 1; x <= 2 * m; x++) {
                    best = Math.max(best, suffix[i] - dp[i + x][Math.max(m, x)]);
                }
                dp[i][m] = best;
            }
        }
        return dp[0][1];
    }

}
