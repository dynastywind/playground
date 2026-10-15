package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC3193
 */
public class CountNumberOfInversions {

    public int numberOfPermutations(int n, int[][] requirements) {
        final int MOD = 1_000_000_007;

        int maxCnt = 0;
        int[] req = new int[n];
        Arrays.fill(req, -1);
        for (int[] requirement : requirements) {
            req[requirement[0]] = requirement[1];
            maxCnt = Math.max(maxCnt, requirement[1]);
        }
        if (req[0] > 0) {
            return 0;
        }

        int m = maxCnt + 1;
        int[] f = new int[m];
        f[0] = 1;

        int[] pre = new int[m + 1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pre[j + 1] = (pre[j] + f[j]) % MOD;
            }
            int[] g = new int[m];
            int c = req[i];
            int lo = Math.max(c, 0);
            if (lo >= m) {
                return 0;
            }
            int hi = c >= 0 ? c : m - 1;
            for (int j = lo; j <= hi; j++) {
                int from = Math.max(0, j - i);
                g[j] = Math.floorMod(pre[j + 1] - pre[from], MOD);
            }
            f = g;
        }
        return f[req[n - 1]];
    }

}
