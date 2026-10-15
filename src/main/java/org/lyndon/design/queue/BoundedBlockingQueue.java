package org.lyndon.design.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue<E> {

    private final Object[] items;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private int head = 0, tail = 0, count = 0;

    public BoundedBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.items = new Object[capacity];
    }

    public boolean offer(E e) {
        lock.lock();
        try {
            if (count == items.length) {
                return false;
            }
            items[tail] = e;
            tail = (tail + 1) % items.length;
            count++;
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while (count == items.length) {
                if (nanos <= 0) {
                    return false;
                }
                nanos = notFull.awaitNanos(nanos);
            }
            items[tail] = e;
            tail = (tail + 1) % items.length;
            count++;
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void put(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[tail] = e;
            tail = (tail + 1) % items.length;
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            E e = (E) items[head];
            items[head] = null;
            head = (head + 1) % items.length;
            count--;
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                if (nanos <= 0) {
                    return null;
                }
                // to avoid spurious wakeup
                nanos = notEmpty.awaitNanos(nanos);
            }
            E e = (E) items[head];
            head = (head + 1) % items.length;
            count--;
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

}
