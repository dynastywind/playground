package org.lyndon.algo.dp;

/**
 * LC2771
 */
public class LongestNonDecreasingSubarrayFromTwoArrays {

    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int ans = 1;
        int a = 1;
        int b = 1;
        for (int i = 1; i < n; i++) {
            int na = 1;
            int nb = 1;
            if (nums1[i - 1] <= nums1[i]) {
                na = Math.max(na, a + 1);
            }
            if (nums2[i - 1] <= nums1[i]) {
                na = Math.max(na, b + 1);
            }
            if (nums1[i - 1] <= nums2[i]) {
                nb = Math.max(nb, a + 1);
            }
            if (nums2[i - 1] <= nums2[i]) {
                nb = Math.max(nb, b + 1);
            }
            ans = Math.max(ans, Math.max(na, nb));
            a = na;
            b = nb;
        }
        return ans;
    }

    static void main() {
        var solution = new LongestNonDecreasingSubarrayFromTwoArrays();
        System.out.println(solution.maxNonDecreasingLength(new int[]{2, 3, 1}, new int[]{1, 2, 1}));
        System.out.println(solution.maxNonDecreasingLength(new int[]{1, 3, 2, 1}, new int[]{2, 2, 3, 4}));
        System.out.println(solution.maxNonDecreasingLength(new int[]{1, 1}, new int[]{2, 2}));
        System.out.println(solution.maxNonDecreasingLength(new int[]{12, 13, 5}, new int[]{7, 10, 1}));
        System.out.println(solution.maxNonDecreasingLength(new int[]{3, 19, 13, 19}, new int[]{20, 18, 7, 14}));
    }

}
