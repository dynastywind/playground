package org.lyndon.algo.array;

/**
 * LC2575
 */
public class FindDivisibilityArrayOfString {

    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] ans = new int[n];
        long r = 0;
        for (int i = 0; i < n; i++) {
            int d = word.charAt(i) - '0';
            r = (r * 10 + d) % m;
            if (r == 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }

}
