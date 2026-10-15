package org.lyndon.algo.array;

/**
 * LC2179
 * You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n - 1].
 * <p>
 * A good triplet is a set of 3 distinct values which are present in increasing order by position both in nums1 and nums2. In other words, if we consider pos1v as the index of the value v in nums1 and pos2v as the index of the value v in nums2, then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1, such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.
 * <p>
 * Return the total number of good triplets.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
 * Output: 1
 * Explanation:
 * There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z. They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
 * Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.
 * Example 2:
 * <p>
 * Input: nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
 * Output: 4
 * Explanation: The 4 good triplets are (4,0,3), (4,0,2), (4,1,3), and (4,1,2).
 */
public class CountGoodTriplets {

    private static class BIT {
        private final int[] tree;

        public BIT(int n) {
            this.tree = new int[n + 1];
        }

        public int query(int node) {
            int s = 0;
            for (int i = node; i >= 0; i -= i & (-i)) {
                s += tree[i];
            }
            return s;
        }

        public void update(int node, int delta) {
            for (int i = node; i < tree.length; i += i & (-i)) {
                tree[i] += delta;
            }
        }
    }

    /*
        pos[v] = index of v in nums1
        a[i] = pos[nums2[i]] = position of a value from nums2 in nums1
        find how many triplets that satisfy: i < j < k and a[i] < a[j] < a[k]

        fix a[j]
        ans = ∑ left[j] * right[j]
        left[j] = elements to the left of a[j] that are smaller than a[j]
        right[j] =  elements to the right of a[j] that are larger than a[j]
        right[j] = (length of array - all items that are larger than a[j]) - (elements to the left of a[j] that are larger than a[j])
                 = (n - 1 - a[j]) - (j - left[j])
     */
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums1[i]] = i;
        }
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = pos[nums2[i]];
        }

        BIT tree = new BIT(n);
        long ans = 0;
        for (int j = 0; j < n; j++) {
            long left = tree.query(a[j]);
            long right = n - 1 - a[j] - (j - left);
            ans += left * right;
            tree.update(a[j] + 1, 1);
        }
        return ans;
    }

}
