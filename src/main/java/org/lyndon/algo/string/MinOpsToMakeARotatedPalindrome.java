package org.lyndon.algo.string;

/**
 * LC4021
 */
public class MinOpsToMakeARotatedPalindrome {

    public int minOperations(String s) {
        int n = s.length();
        int ans = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
            int cost = k;
            for (int i = 0, j = n - 1; i < j; i++, j--) {
                int a = s.charAt((i + k) % n);
                int b = s.charAt((j + k) % n);
                int d = Math.abs(a - b);
                cost += Math.min(d, 26 - d);
                if (cost >= ans) {
                    break;
                }
            }
            ans = Math.min(ans, cost);
        }
        return ans;
    }

}
