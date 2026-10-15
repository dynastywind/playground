package org.lyndon.algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * LC759
 */
public class EmployeeFreeTime {

    private static class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    ;

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> events = new ArrayList<>();
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                events.add(new int[]{interval.start, -1});
                events.add(new int[]{interval.end, 1});
            }
        }
        events.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        List<Interval> result = new ArrayList<>();
        int[] prev = null;
        int active = 0;
        for (int[] event : events) {
            if (active == 0 && prev != null) {
                result.add(new Interval(prev[0], event[0]));
            }
            active -= event[1];
            prev = event;
        }
        return result;
    }

}
