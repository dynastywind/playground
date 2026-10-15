package org.lyndon.design;

import java.util.ArrayList;
import java.util.List;

/**
 * LC3709
 */
public class ExamTracker {

    private final List<Integer> times;
    private final List<Long> pre;

    public ExamTracker() {
        this.times = new ArrayList<>();
        this.pre = new ArrayList<>();
        this.pre.add(0L);
    }

    public void record(int time, int score) {
        times.add(time);
        pre.add(pre.getLast() + score);
    }

    public long totalScore(int startTime, int endTime) {
        int l = lowerBound(startTime);
        int h = lowerBound(endTime + 1);
        return pre.get(h) - pre.get(l);
    }

    // first value >= x
    private int lowerBound(int x) {
        int lo = 0;
        int hi = times.size();
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (times.get(mid) < x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
