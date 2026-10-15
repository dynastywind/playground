package org.lyndon.algo.array;

/**
 * LC3312
 */
public class SortedGCDPairQueries {

    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        long[] cntDiv = new long[max + 1];
        for (int x : nums) {
            cntDiv[x]++;
        }
        for (int d = 1; d <= max; d++) {
            for (int m = 2 * d; m <= max; m += d) {
                cntDiv[d] += cntDiv[m];
            }
        }

        long[] pairs = new long[max + 1];
        for (int d = 1; d <= max; d++) {
            pairs[d] = cntDiv[d] * (cntDiv[d] - 1) / 2;
        }

        for (int d = max / 2; d >= 1; d--) {
            for (int m = 2 * d; m <= max; m += d) {
                pairs[d] -= pairs[m];
            }
        }

        long[] pre = new long[max + 1];
        for (int d = 1; d <= max; d++) {
            pre[d] = pre[d - 1] + pairs[d];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int lo = 1;
            int hi = max;
            while (lo < hi) {
                int mid = (lo + hi) >> 1;
                if (pre[mid] > q) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            ans[i] = lo;
        }
        return ans;
    }

}
