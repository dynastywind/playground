package org.lyndon.algo.tree;

/**
 * LC3739
 */
public class CountSubarraysWithMajorityII {

    private static class BIT {
        int[] tree;

        public BIT(int n) {
            this.tree = new int[n + 1];
        }

        public void update(int node, int val) {
            for (int i = node; i < tree.length; i += i & (-i)) {
                tree[i] += val;
            }
        }

        public int query(int node) {
            int res = 0;
            for (int i = node; i > 0; i -= i & (-i)) {
                res += tree[i];
            }
            return res;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        // [-n, n] -> 2n + 1
        BIT tree = new BIT(2 * n + 1);
        tree.update(n + 1, 1);
        long ans = 0;
        int pre = 0;
        for (int num : nums) {
            pre += (num == target) ? 1 : -1;
            ans += tree.query(pre + n);
            tree.update(pre + n + 1, 1);
        }
        return ans;
    }

}
