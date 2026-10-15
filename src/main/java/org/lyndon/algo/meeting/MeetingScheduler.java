package org.lyndon.algo.meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * LC1229
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 * <p>
 * If there is no common time slot that satisfies the requirements, return an empty array.
 * <p>
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 * <p>
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 * <p>
 * Example 1:
 * <p>
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 * Example 2:
 * <p>
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= slots1.length, slots2.length <= 104
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 109
 * 1 <= duration <= 106
 */
public class MeetingScheduler {

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(slots2, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0, j = 0; i < slots1.length && j < slots2.length; ) {
            int start = Math.max(slots1[i][0], slots2[j][0]);
            int end = Math.min(slots1[i][1], slots2[j][1]);
            if (end > start && end - start >= duration) {
                return List.of(start, start + duration);
            }
            if (slots1[i][1] <= slots2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return new ArrayList<>();
    }

}
