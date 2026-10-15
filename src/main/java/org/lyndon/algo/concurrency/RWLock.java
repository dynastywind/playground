package org.lyndon.algo.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RWLock {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition read = lock.newCondition();
    private final Condition write = lock.newCondition();

    private int readers = 0;
    private int waitingWriters = 0;
    private boolean writing;

    public void readLock() throws InterruptedException {
        lock.lock();
        try {
            while (writing || waitingWriters > 0) {
                read.await();
            }
            readers++;
        } finally {
            lock.unlock();
        }
    }

    public void readUnlock() {
        lock.lock();
        try {
            if (--readers == 0) {
                write.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void writeLock() throws InterruptedException {
        lock.lock();
        try {
            waitingWriters++;
            try {
                while (writing || readers > 0) {
                    write.await();
                }
            } finally {
                waitingWriters--;
            }
            writing = true;
        } finally {
            lock.unlock();
        }
    }

    public void writeUnlock() {
        lock.lock();
        try {
            writing = false;
            write.signal();
            read.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
