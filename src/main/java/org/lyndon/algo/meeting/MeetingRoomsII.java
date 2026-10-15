package org.lyndon.algo.meeting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LC 253
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Example 2:
 * <p>
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
public class MeetingRoomsII {

    /*
        0, 5, 10!, 15, 20!, 30!
     */
    public int minMeetingRooms(int[][] intervals) {
        int[][] events = new int[intervals.length * 2][2];
        for (int i = 0; i < events.length; i += 2) {
            events[i] = new int[]{intervals[i / 2][0], 1};
            events[i + 1] = new int[]{intervals[i / 2][1], 0};
        }
        Arrays.sort(events, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        int ans = 0;
        int cnt = 0;
        for (int[] event : events) {
            if (event[1] == 1) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new MeetingRoomsII();
        System.out.println(solution.minMeetingRooms(new int[][]{new int[]{0, 30}, new int[]{5, 10}, new int[]{15, 20}}));
        System.out.println(solution.minMeetingRooms(new int[][]{new int[]{13, 15}, new int[]{1, 13}}));
    }

}
