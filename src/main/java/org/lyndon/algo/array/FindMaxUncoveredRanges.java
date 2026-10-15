package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * LC2655
 */
public class FindMaxUncoveredRanges {

    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {
        List<int[]> timeline = new ArrayList<>();
        for (int[] range : ranges) {
            timeline.add(new int[]{range[0], 1});
            timeline.add(new int[]{range[1], -1});
        }
        timeline.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });
        List<int[]> result = new ArrayList<>();
        int prev = -1;
        int cnt = 0;
        for (int[] t : timeline) {
            cnt += t[1];
            if (cnt == 1 && t[1] > 0) {
                int start = prev + 1;
                int end = t[0] - 1;
                if (end >= start) {
                    result.add(new int[]{start, end});
                }
            } else if (cnt == 0) {
                prev = t[0];
            }
        }
        if (n - 1 > prev) {
            result.add(new int[]{prev + 1, n - 1});
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        var solution = new FindMaxUncoveredRanges();
        PrintUtils.printArray(solution.findMaximalUncoveredRanges(10, new int[][]{{3, 5}, {7, 8}}));
        PrintUtils.printArray(solution.findMaximalUncoveredRanges(3, new int[][]{{0, 2}}));
        PrintUtils.printArray(solution.findMaximalUncoveredRanges(7, new int[][]{{2, 4}, {0, 3}}));
        PrintUtils.printArray(solution.findMaximalUncoveredRanges(7, new int[][]{{0, 4}, {4, 6}}));
        PrintUtils.printArray(solution.findMaximalUncoveredRanges(10, new int[][]{{0, 0}, {6, 6}, {0, 2}, {9, 9}}));
    }

}
