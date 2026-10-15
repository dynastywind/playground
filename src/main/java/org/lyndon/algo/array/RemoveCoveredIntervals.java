package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC1288
 * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.
 * <p>
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
 * <p>
 * Return the number of remaining intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 */
public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int maxEnd = Integer.MIN_VALUE;
        int count = 0;
        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                maxEnd = interval[1];
                count++;
            }
        }
        return count;
    }

}
