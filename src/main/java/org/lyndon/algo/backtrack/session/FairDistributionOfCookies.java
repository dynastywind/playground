package org.lyndon.algo.backtrack.session;

/**
 * LC2305
 * You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag. You are also given an integer k that denotes the number of children to distribute all the bags of cookies to. All the cookies in the same bag must go to the same child and cannot be split up.
 * <p>
 * The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.
 * <p>
 * Return the minimum unfairness of all distributions.
 * <p>
 * Example 1:
 * <p>
 * Input: cookies = [8,15,10,20,8], k = 2
 * Output: 31
 * Explanation: One optimal distribution is [8,15,8] and [10,20]
 * - The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
 * - The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
 * The unfairness of the distribution is max(31,30) = 31.
 * It can be shown that there is no distribution with an unfairness less than 31.
 * Example 2:
 * <p>
 * Input: cookies = [6,1,3,2,2,4,1,2], k = 3
 * Output: 7
 * Explanation: One optimal distribution is [6,1], [3,2,2], and [4,1,2]
 * - The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
 * - The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
 * - The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
 * The unfairness of the distribution is max(7,7,7) = 7.
 * It can be shown that there is no distribution with an unfairness less than 7.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= cookies.length <= 8
 * 1 <= cookies[i] <= 105
 * 2 <= k <= cookies.length
 */
public class FairDistributionOfCookies {

    private int ans = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        int[] children = new int[k];
        backtrack(cookies, children, 0);
        return ans;
    }

    private void backtrack(int[] cookies, int[] children, int i) {
        if (i == cookies.length) {
            int max = 0;
            for (int child : children) {
                max = Math.max(max, child);
            }
            ans = Math.min(ans, max);
            return;
        }
        for (int j = 0; j < children.length; j++) {
            if (children[j] + cookies[i] >= ans) {
                continue;
            }

            children[j] += cookies[i];
            backtrack(cookies, children, i + 1);
            children[j] -= cookies[i];
        }
    }

    private int distributeCookiesFullDP(int[] cookies, int k) {
        int n = cookies.length;
        int full = 1 << n;
        int[] sum = new int[full];
        for (int s = 1; s < full; s++) {
            int low = Integer.numberOfTrailingZeros(s);
            sum[s] = cookies[low] + sum[s & (s - 1)];
        }
        int[][] dp = new int[k][full];
        dp[0] = sum.clone();
        for (int i = 1; i < k; i++) {
            for (int s = 0; s < full; s++) {
                dp[i][s] = dp[i - 1][s];
                for (int t = s; t > 0; t = (t - 1) & s) {
                    dp[i][s] = Math.min(dp[i][s], Math.max(dp[i - 1][s ^ t], sum[t]));
                }
            }
        }
        return dp[k - 1][full - 1];
    }

    public int distributeCookiesDP(int[] cookies, int k) {
        int n = cookies.length;
        int full = 1 << n;
        int[] sum = new int[full];
        for (int s = 1; s < full; s++) {
            int low = Integer.numberOfTrailingZeros(s);
            sum[s] = cookies[low] + sum[s & (s - 1)];
        }
        int[] dp = sum.clone();
        for (int i = 2; i <= k; i++) {
            for (int s = full - 1; s > 0; s--) {
                for (int t = s; t > 0; t = (t - 1) & s) {
                    dp[s] = Math.min(dp[s], Math.max(dp[s ^ t], sum[t]));
                }
            }
        }
        return dp[full - 1];
    }

}
