package org.lyndon.design;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LC1188
 * Implement a thread-safe bounded blocking queue that has the following methods:
 * <p>
 * BoundedBlockingQueue(int capacity) The constructor initializes the queue with a maximum capacity.
 * void enqueue(int element) Adds an element to the front of the queue. If the queue is full, the calling thread is blocked until the queue is no longer full.
 * int dequeue() Returns the element at the rear of the queue and removes it. If the queue is empty, the calling thread is blocked until the queue is no longer empty.
 * int size() Returns the number of elements currently in the queue.
 * Your implementation will be tested using multiple threads at the same time. Each thread will either be a producer thread that only makes calls to the enqueue method or a consumer thread that only makes calls to the dequeue method. The size method will be called after every test case.
 * <p>
 * Please do not use built-in implementations of bounded blocking queue as this will not be accepted in an interview.
 */
public class BoundedBlockingQueue {

    private final int capacity;
    private final Queue<Integer> queue;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayDeque<>();
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.offer(element);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            notFull.signal();
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

}
