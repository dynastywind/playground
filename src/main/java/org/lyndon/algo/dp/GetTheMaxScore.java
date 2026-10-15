package org.lyndon.algo.dp;

/**
 * LC1537
 */
public class GetTheMaxScore {

    public int maxSum(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = m - 1;
        int j = n - 1;
        int max = Math.max(nums1[i], nums2[j]);
        long[] dp = new long[max + 1];
        while (i >= 0 && j >= 0) {
            if (nums1[i] == nums2[j]) {
                long a = nums1[i] + (i == m - 1 ? 0 : dp[nums1[i + 1]]);
                long b = nums2[j] + (j == n - 1 ? 0 : dp[nums2[j + 1]]);
                dp[nums1[i]] = Math.max(a, b);
                i--;
                j--;
            } else if (nums1[i] > nums2[j]) {
                dp[nums1[i]] = nums1[i] + (i == m - 1 ? 0 : dp[nums1[i + 1]]);
                i--;
            } else {
                dp[nums2[j]] = nums2[j] + (j == n - 1 ? 0 : dp[nums2[j + 1]]);
                j--;
            }
        }
        while (i >= 0) {
            dp[nums1[i]] = nums1[i] + (i == m - 1 ? 0 : dp[nums1[i + 1]]);
            i--;
        }
        while (j >= 0) {
            dp[nums2[j]] = nums2[j] + (j == n - 1 ? 0 : dp[nums2[j + 1]]);
            j--;
        }
        return (int) (Math.max(dp[nums1[0]], dp[nums2[0]]) % 1_000_000_007);
    }

    public static void main(String[] args) {
        GetTheMaxScore solution = new GetTheMaxScore();
        System.out.println(solution.maxSum(new int[]{2, 4, 5, 8, 10}, new int[]{4, 6, 8, 9}));
        System.out.println(solution.maxSum(new int[]{1, 3, 5, 7, 9}, new int[]{3, 5, 100}));
        System.out.println(solution.maxSum(new int[]{1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10}));
    }

}
