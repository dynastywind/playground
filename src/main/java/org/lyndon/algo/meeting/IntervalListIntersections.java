package org.lyndon.algo.meeting;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LC986
 */
public class IntervalListIntersections {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // {time, type}
        PriorityQueue<int[]> events = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        for (int[] first : firstList) {
            events.add(new int[]{first[0], -1});
            events.add(new int[]{first[1], 1});
        }
        for (int[] second : secondList) {
            events.add(new int[]{second[0], -1});
            events.add(new int[]{second[1], 1});
        }

        int cnt = 0;
        List<int[]> res = new ArrayList<>();
        int start = -1;
        while (!events.isEmpty()) {
            int[] event = events.poll();
            if (event[1] == -1) {
                if (++cnt == 2) {
                    start = event[0];
                }
            } else {
                if (cnt-- == 2) {
                    res.add(new int[]{start, event[0]});
                }
            }
        }
        return res.toArray(new int[0][]);
    }


    public int[][] intervalIntersection2(int[][] firstList, int[][] secondList) {
        int m = firstList.length;
        int n = secondList.length;
        int i = 0;
        int j = 0;
        List<int[]> res = new ArrayList<>();
        while (i < m && j < n) {
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            if (start <= end) {
                res.add(new int[]{start, end});
            }
            if (firstList[i][1] > secondList[j][1]) {
                j++;
            } else if (secondList[j][1] > firstList[i][1]) {
                i++;
            } else {
                i++;
                j++;
            }
        }
        return res.toArray(new int[0][]);
    }

    static void main() {
        var solution = new IntervalListIntersections();
        PrintUtils.printArray(solution.intervalIntersection(new int[][]{{5, 10}}, new int[][]{{5, 10}}));
    }

}
