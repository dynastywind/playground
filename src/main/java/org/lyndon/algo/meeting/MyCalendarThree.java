package org.lyndon.algo.meeting;

import java.util.TreeMap;

public class MyCalendarThree {

    private final TreeMap<Integer, Integer> map;

    public MyCalendarThree() {
        this.map = new TreeMap<>();
    }

    public int book(int startTime, int endTime) {
        map.merge(startTime, 1, Integer::sum);
        map.merge(endTime, -1, Integer::sum);
        int max = 0;
        int cnt = 0;
        for (int v : map.values()) {
            cnt += v;
            max = Math.max(max, cnt);
        }
        return max;
    }

}
