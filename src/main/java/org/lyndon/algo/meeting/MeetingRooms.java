package org.lyndon.algo.meeting;

import java.util.Arrays;

/**
 * LC252
 * You are given an array of meeting times intervals where intervals[i] = [starti, endi].
 * <p>
 * A person can attend all meetings if no two meeting intervals overlap. Meetings ending at time t and starting at time t do not overlap.
 * <p>
 * Return true if a person can attend all meetings. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 * <p>
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti < endi <= 106
 */
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

}
