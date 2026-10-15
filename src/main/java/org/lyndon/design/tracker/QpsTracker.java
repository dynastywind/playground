package org.lyndon.design.tracker;

import java.util.ArrayList;
import java.util.List;

public class QpsTracker {

    private final List<long[]> prefix = new ArrayList<>();

    private long total = 0;

    public void record(long t) {
        total++;
        int n = prefix.size();
        if (n > 0 && prefix.getLast()[0] == t) {
            prefix.getLast()[1] = total;
        } else {
            prefix.add(new long[]{t, total});
        }
    }

    public double getQps(long start, long end) {
        if (end < start) {
            throw new IllegalArgumentException("end < start");
        }
        long cnt = countUpTo(end) - countUpTo(start - 1);
        return (double) cnt / (end - start + 1);
    }

    private long countUpTo(long t) {
        int lo = 0;
        int hi = prefix.size() - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (prefix.get(mid)[0] <= t) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans < 0 ? 0 : prefix.get(ans)[1];
    }

}
