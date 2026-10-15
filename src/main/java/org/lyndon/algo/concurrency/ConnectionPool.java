package org.lyndon.algo.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ConnectionPool implements AutoCloseable {

    interface Conn {
        void close();
    }

    private final BlockingQueue<Conn> idle;
    private final Semaphore permits;
    private final Supplier<Conn> factory;
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public ConnectionPool(int max, Supplier<Conn> factory) {
        this.idle = new ArrayBlockingQueue<>(max);
        this.permits = new Semaphore(max);
        this.factory = factory;
    }

    public Conn acquire(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
        if (closed.get()) {
            throw new IllegalStateException("Pool is closed");
        }
        if (!permits.tryAcquire(timeout, unit)) {
            throw new TimeoutException();
        }
        Conn c = idle.poll();
        try {
            if (c == null) {
                c = factory.get();
            }
            return c;
        } catch (RuntimeException e) {
            permits.release();
            throw e;
        }
    }

    public void release(Conn c) {
        if (c == null) {
            return;
        }
        if (closed.get()) {
            c.close();
            permits.release();
            return;
        }
        idle.offer(c);
        permits.release();
    }

    @Override
    public void close() {
        if (!closed.compareAndExchange(false, true)) {
            return;
        }
        Conn c;
        while ((c = idle.poll()) != null) {
            c.close();
        }
    }

}
