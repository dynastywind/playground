package org.lyndon.design.rate;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowRateLimiter implements RateLimiter {

    private final long windowNanos;

    private final int limit;

    private final Deque<Long> log = new ArrayDeque<>();

    public SlidingWindowRateLimiter(long windowNanos, int limit) {
        this.windowNanos = windowNanos;
        this.limit = limit;
    }

    @Override
    public boolean tryAcquire() {
        long now = System.nanoTime();
        while (!log.isEmpty() && now - log.peekFirst() >= windowNanos) {
            log.pollFirst();
        }

        if (log.size() < limit) {
            log.addLast(now);
            return true;
        }
        return false;
    }

}
