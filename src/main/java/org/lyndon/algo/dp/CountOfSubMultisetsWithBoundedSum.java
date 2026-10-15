package org.lyndon.algo.dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC2902
 */
public class CountOfSubMultisetsWithBoundedSum {

    public int countSubMultisets(List<Integer> nums, int l, int r) {
        final int MOD = 1_000_000_007;
        Map<Integer, Integer> cnt = new HashMap<>();
        int total = 0;
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
            total += num;
        }
        if (total < l) {
            return 0;
        }
        r = Math.min(r, total);

        int zeros = cnt.getOrDefault(0, 0);
        cnt.remove(0);

        long[] dp = new long[r + 1];
        dp[0] = 1;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int v = entry.getKey();
            int c = entry.getValue();
            int window = (c + 1) * v;
            long[] nd = new long[r + 1];
            for (int s = 0; s <= r; s++) {
                long val = dp[s];
                if (s >= v) {
                    val += nd[s - v];
                }
                if (s >= window) {
                    val -= dp[s - window];
                }
                nd[s] = Math.floorMod(val, MOD);
            }
            dp = nd;
        }

        long ans = 0;
        for (int s = l; s <= r; s++) {
            ans += dp[s];
        }
        ans %= MOD;
        return (int) (ans * (zeros + 1) % MOD);
    }

}
