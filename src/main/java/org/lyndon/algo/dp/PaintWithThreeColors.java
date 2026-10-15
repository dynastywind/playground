package org.lyndon.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC1931
 */
public class PaintWithThreeColors {

    public int colorTheGrid(int m, int n) {
        final int MOD = 1_000_000_007;

        int total = 1;
        for (int i = 0; i < m; i++) {
            total *= 3;
        }

        List<int[]> valid = new ArrayList<>();
        for (int mask = 0; mask < total; mask++) {
            int[] color = new int[m];
            boolean ok = true;
            int x = mask;
            for (int i = 0; i < m; i++) {
                color[i] = x % 3;
                x /= 3;
                if (i > 0 && color[i] == color[i - 1]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                valid.add(color);
            }
        }
        int k = valid.size();

        List<List<Integer>> next = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            List<Integer> list = new ArrayList<>();
            int[] a = valid.get(i);
            for (int j = 0; j < k; j++) {
                int[] b = valid.get(j);
                boolean ok = true;
                for (int r = 0; r < m; r++) {
                    if (a[r] == b[r]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    list.add(j);
                }
            }
            next.add(list);
        }

        long[] dp = new long[k];
        Arrays.fill(dp, 1L);
        for (int c = 1; c < n; c++) {
            long[] g = new long[k];
            for (int i = 0; i < k; i++) {
                for (int j : next.get(i)) {
                    g[j] = (g[j] + dp[i]) % MOD;
                }
            }
            dp = g;
        }
        long ans = 0;
        for (long v : dp) {
            ans = (ans + v) % MOD;
        }
        return (int) ans;
    }

}
