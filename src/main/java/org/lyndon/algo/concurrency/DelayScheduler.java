package org.lyndon.algo.concurrency;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DelayScheduler {

    record Task(long nanos, long seq, Runnable job) {
    }

    private final PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparing(Task::nanos).thenComparing(Task::seq));
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition available = lock.newCondition();

    private Thread leader;
    private long seq = 0;

    public void schedule(Runnable job, long delayNanos) {
        lock.lock();
        try {
            Task t = new Task(System.nanoTime() + delayNanos, seq++, job);
            pq.offer(t);
            // new task becomes the first to be scheduled. discard current leader to trigger take()
            if (pq.peek() == t) {
                leader = null;
                available.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public Runnable take() throws InterruptedException {
        lock.lock();
        try {
            while (true) {
                Task head = pq.peek();
                if (head == null) {
                    available.await();
                    continue;
                }
                long rem = head.nanos() - System.nanoTime();
                if (rem <= 0) {
                    if (leader == null && pq.peek() != null) {
                        available.signal();
                    }
                    return head.job();
                }
                if (leader != null) {
                    available.await();
                } else {
                    Thread self = Thread.currentThread();
                    leader = self;
                    try {
                        available.awaitNanos(rem);
                    } finally {
                        if (leader == self) {
                            leader = null;
                        }
                    }
                }
            }
        } finally {
            if (leader == null && pq.peek() != null) {
                available.signal();
            }
            lock.unlock();
        }
    }

}
