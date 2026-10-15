package org.lyndon.algo.dp;

/**
 * LC1911
 */
public class MaxAlternatingSubsequenceSum {

    /*
        odd[i] = max(odd[i-1], even[i-1] + nums[i])
        odd[0] = nums[0]
        even[i] = max(even[i-1], odd[i-1] - nums[i])
        even[0] = 0
     */
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        long odd = nums[0];
        long even = 0;
        for (int i = 1; i < n; i++) {
            long no = Math.max(odd, even + nums[i]);
            long ne = Math.max(even, odd - nums[i]);
            odd = no;
            even = ne;
        }
        return odd;
    }

    static void main() {
        var solution = new MaxAlternatingSubsequenceSum();
        System.out.println(solution.maxAlternatingSum(new int[]{4, 2, 5, 3}));
        System.out.println(solution.maxAlternatingSum(new int[]{5, 6, 7, 8}));
        System.out.println(solution.maxAlternatingSum(new int[]{6, 2, 1, 2, 4, 5}));
    }

}
