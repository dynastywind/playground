package org.lyndon.algo.array;

/**
 * LC1330
 */
public class ReverseSubarrayToMaximizeArrayValue {

    /*
        2,3,1,5,4 => |2-3|+|3-1|+|1-5|+|5-4|
        5,1,3,2,4 => |5-1|+|1-3|+|3-2|+|2-4|
        delta = |n[0]-n[p+1]| - |n[p]-n[p+1]|

        2,4,5,1,3 => |2-4|+|4-5|+|5-1|+|1-3|
        delta = |n[p-1]-n[n-1]|-|n[p-1]-n[p]|

        2,5,1,3,4 => |2-5|+|5-1|+|1-3|+|3-4|
        delta = |n[p-1]-n[q]| + |n[p]-n[q+1]|
              -(|n[p-1]-n[p]| + |n[q]-n[q+1]|)
        0 < p < q < n - 1
        a = min(n[p-1],n[p])
        b = max(n[p-1],n[p])
        c = min(n[q],n[q+1])
        d = max(n[q],n[q+1])
        delta = [(c - a) + (d - b)] - [(b - a) + (d - c)] = 2(c - b)
     */
    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        long base = 0;
        for (int i = 0; i < n - 1; i++) {
            base += Math.abs(nums[i] - nums[i + 1]);
        }

        long best = 0;
        int minMax = Integer.MAX_VALUE;
        int maxMin = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            minMax = Math.min(minMax, Math.max(nums[i], nums[i + 1]));
            maxMin = Math.max(maxMin, Math.min(nums[i], nums[i + 1]));
        }
        best = Math.max(best, 2L * (maxMin - minMax));

        for (int i = 0; i < n - 1; i++) {
            long prefix = Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]);
            long suffix = Math.abs(nums[i] - nums[n - 1]) - Math.abs(nums[i] - nums[i + 1]);
            best = Math.max(best, Math.max(prefix, suffix));
        }
        return (int) (base + best);
    }

}
