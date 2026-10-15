package org.lyndon.algo.tree;

/**
 * LC2407
 * You are given an integer array nums and an integer k.
 * <p>
 * Find the longest subsequence of nums that meets the following requirements:
 * <p>
 * The subsequence is strictly increasing and
 * The difference between adjacent elements in the subsequence is at most k.
 * Return the length of the longest subsequence that meets the requirements.
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
 * Output: 5
 * Explanation:
 * The longest subsequence that meets the requirements is [1,3,4,5,8].
 * The subsequence has a length of 5, so we return 5.
 * Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.
 * Example 2:
 * <p>
 * Input: nums = [7,4,5,1,8,12,4,7], k = 5
 * Output: 4
 * Explanation:
 * The longest subsequence that meets the requirements is [4,5,8,12].
 * The subsequence has a length of 4, so we return 4.
 * Example 3:
 * <p>
 * Input: nums = [1,5], k = 1
 * Output: 1
 * Explanation:
 * The longest subsequence that meets the requirements is [1].
 * The subsequence has a length of 1, so we return 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 105
 */
public class LongestIncreasingSubsequenceII {

    private int[] tree;
    private int m;

    public int lengthOfLIS(int[] nums, int k) {
        for (int num : nums) {
            m = Math.max(m, num);
        }
        tree = new int[m << 2];

        int ans = 0;
        for (int num : nums) {
            int cur = 1;
            if (num > 1) {
                int lo = Math.max(1, num - k);
                cur = query(1, 1, m, lo, num - 1) + 1;
            }
            ans = Math.max(ans, cur);
            update(1, 1, m, num, cur);
        }
        return ans;
    }

    private void update(int node, int l, int r, int i, int val) {
        if (l == r) {
            tree[node] = Math.max(tree[node], val);
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            update(node << 1, l, mid, i, val);
        } else {
            update(node << 1 | 1, mid + 1, r, i, val);
        }
        tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
    }

    private int query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int res = 0;
        int mid = (l + r) >> 1;
        if (ql <= mid) {
            res = Math.max(res, query(node << 1, l, mid, ql, qr));
        }
        if (mid < qr) {
            res = Math.max(res, query(node << 1 | 1, mid + 1, r, ql, qr));
        }
        return res;
    }

}
