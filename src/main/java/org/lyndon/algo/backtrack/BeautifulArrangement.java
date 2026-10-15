package org.lyndon.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * LC526
 */
public class BeautifulArrangement {

    private int count;

    public int countArrangement(int n) {
        List<Integer>[] match = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            match[i] = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    match[i].add(j);
                }
            }
        }
        backtrack(n, match, new boolean[n + 1]);
        return count;
    }

    private void backtrack(int pos, List<Integer>[] match, boolean[] used) {
        if (pos == 0) {
            count++;
            return;
        }
        for (int x : match[pos]) {
            if (used[x]) {
                continue;
            }
            used[x] = true;
            backtrack(pos - 1, match, used);
            used[x] = false;
        }
    }

    public int countArrangement2(int n) {
        int full = 1 << n;
        int[] dp = new int[full];
        dp[0] = 1;
        for (int mask = 1; mask < full; mask++) {
            int pos = Integer.bitCount(mask);
            for (int j = 1; j <= n; j++) {
                if ((mask & (1 << (j - 1))) != 0 && (j % pos == 0 || pos % j == 0)) {
                    dp[mask] += dp[mask ^ (1 << (j - 1))];
                }
            }
        }
        return dp[full - 1];
    }

}
