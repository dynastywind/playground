package org.lyndon.algo.array;

/**
 * LC2376
 */
public class CountSpecialNumbers {

    public int countSpecialNumbers(int n) {
        char[] cs = Integer.toString(n).toCharArray();
        int m = cs.length;
        int ans = 0;

        // pos < m
        for (int i = 1; i < m; i++) {
            int cnt = 9;
            for (int j = 0; j < i - 1; j++) {
                cnt *= 9 - j;
            }
            ans += cnt;
        }

        // pos == m
        int mask = 0;
        for (int i = 0; i < m; i++) {
            int cur = cs[i] - '0';
            for (int j = (i == 0 ? 1 : 0); j < cur; j++) {
                if ((mask >> j & 1) == 1) {
                    continue;
                }
                int cnt = 1;
                int available = 9 - i;
                for (int k = 0; k < m - 1 - i; k++) {
                    cnt *= available - k;
                }
                ans += cnt;
            }
            if ((mask >> cur & 1) == 1) {
                return ans;
            }
            mask |= 1 << cur;
        }
        return ans + 1;
    }

}
