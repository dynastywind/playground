package org.lyndon.algo.sort;

import java.util.Arrays;

/**
 * LC2343
 */
public class QueryKthSmallestTrimmedNumber {

    // O(m(n+10)+q) O(mn)
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length;
        int m = nums[0].length();
        int[][] sorted = new int[m + 1][n];
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        for (int t = 1; t <= m; t++) {
            int pos = m - t;
            int[] cnt = new int[11];
            for (int idx : order) {
                cnt[nums[idx].charAt(pos) - '0' + 1]++;
            }
            for (int d = 1; d <= 10; d++) {
                cnt[d] += cnt[d - 1];
            }
            int[] next = new int[n];
            for (int idx : order) {
                next[cnt[nums[idx].charAt(pos) - '0']++] = idx;
            }
            order = next;
            sorted[t] = order;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = sorted[queries[i][1]][queries[i][0] - 1];
        }
        return ans;
    }

    // O(q*n*m*logn) O(mn)
    public int[] smallestTrimmedNumbers2(String[] nums, int[][] queries) {
        int n = nums.length;
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int k = queries[i][0];
            int trim = queries[i][1];
            String[] arr = new String[n];
            Integer[] pos = new Integer[n];
            for (int j = 0; j < n; j++) {
                String num = nums[j];
                arr[j] = num.substring(num.length() - trim);
                pos[j] = j;
            }
            Arrays.sort(pos, (a, b) -> arr[a].compareTo(arr[b]));
            ans[i] = pos[k - 1];
        }
        return ans;
    }

}
