package org.lyndon.algo.dp;

/**
 * LC2266
 */
public class CountNumberOfTexts {

    public int countTexts(String pressedKeys) {
        final int MOD = 1_000_000_007;
        int n = pressedKeys.length();
        int i = 0;
        long ans = 1;
        while (i < n) {
            int d = pressedKeys.charAt(i) - '0';
            int j = i;
            while (j < n && pressedKeys.charAt(j) - '0' == d) {
                j++;
            }
            int k = (d == 7 || d == 9) ? 4 : 3;
            ans = (ans * count(j - i, k, MOD)) % MOD;
            i = j;
        }
        return (int) ans;
    }

    private long count(int len, int k, int mod) {
        long[] f = new long[k];
        f[0] = 1;
        long sum = 1;
        long ans = 1;
        for (int m = 1; m <= len; m++) {
            long cur = sum;
            long out = (m - k >= 0) ? f[(m - k) % k] : 0;
            f[m % k] = cur;
            sum = Math.floorMod(sum + cur - out, mod);
            ans = cur;
        }
        return ans;
    }

    static void main() {
        var solution = new CountNumberOfTexts();
        System.out.println(solution.countTexts("22233"));
    }

}
