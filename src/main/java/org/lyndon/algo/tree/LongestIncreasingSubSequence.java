package org.lyndon.algo.tree;

import java.util.Arrays;

/**
 * LC 300
 */
public class LongestIncreasingSubSequence {

    private static class BIT {
        private final int[] tree;

        public BIT(int n) {
            this.tree = new int[n + 1];
        }

        public int query(int node) {
            int res = 0;
            for (int i = node; i > 0; i -= i & (-i)) {
                res = Math.max(res, tree[i]);
            }
            return res;
        }

        public void update(int node, int val) {
            for (int i = node; i < tree.length; i += i & (-i)) {
                tree[i] = Math.max(tree[i], val);
            }
        }
    }

    public int lengthOfLIS(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int x : sorted) {
            if (m == 0 || sorted[m - 1] < x) {
                sorted[m++] = x;
            }
        }
        BIT tree = new BIT(m);
        int ans = 0;
        for (int num : nums) {
            // 1-indexed
            int rank = findNum(sorted, m, num) + 1;
            int v = tree.query(rank - 1) + 1;
            ans = Math.max(ans, v);
            tree.update(rank, v);
        }
        return ans;
    }

    private int findNum(int[] sorted, int m, int target) {
        int low = 0;
        int high = m;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (sorted[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // O(n^2)
    public int lengthOfLISDP(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans + 1;
    }

}
