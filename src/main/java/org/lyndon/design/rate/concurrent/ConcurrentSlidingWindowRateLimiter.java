package org.lyndon.design.rate.concurrent;

import org.lyndon.design.rate.RateLimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSlidingWindowRateLimiter implements RateLimiter {

    private final int limit;

    private final long windowNanos;

    private final Deque<Long> log = new ArrayDeque<>();

    private final ReentrantLock lock = new ReentrantLock();

    public ConcurrentSlidingWindowRateLimiter(int limit, long windowNanos) {
        this.limit = limit;
        this.windowNanos = windowNanos;
    }

    @Override
    public boolean tryAcquire() {
        long now = System.nanoTime();
        lock.lock();
        try {
            while (!log.isEmpty() && now - log.peekFirst() >= windowNanos) {
                log.pollFirst();
            }
            if (log.size() < limit) {
                log.addLast(now);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

}
