package org.lyndon.design.counter;

import java.util.Arrays;

public class SlidingWindowCounter {

    private final int W;
    private final long[] count;

    private long lastSec = Long.MIN_VALUE;
    private long total = 0;

    public SlidingWindowCounter(int windowSeconds) {
        if (windowSeconds <= 0) {
            throw new IllegalArgumentException("windowSeconds must be positive");
        }
        this.W = windowSeconds;
        this.count = new long[W];
    }

    public void hit(long nowSec) {
        advance(nowSec);
        count[(int) (nowSec % W)]++;
        total++;
    }

    public long getHits(long nowSec) {
        advance(nowSec);
        return total;
    }

    private void advance(long nowSec) {
        if (lastSec == Long.MIN_VALUE) {
            lastSec = nowSec;
            return;
        }
        if (nowSec <= lastSec) {
            return;
        }
        if (nowSec - lastSec >= W) {
            Arrays.fill(count, 0);
            total = 0;
        } else {
            for (long t = lastSec + 1; t <= nowSec; t++) {
                int idx = (int) (t % W);
                total -= count[idx];
                count[idx] = 0;
            }
        }
        lastSec = nowSec;
    }

}
