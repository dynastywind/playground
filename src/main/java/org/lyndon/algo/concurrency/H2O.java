package org.lyndon.algo.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class H2O {

    private final Semaphore h;
    private final Semaphore o;

    private final CyclicBarrier barrier;

    public H2O() {
        this.h = new Semaphore(2);
        this.o = new Semaphore(1);
        this.barrier = new CyclicBarrier(3);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {}

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        h.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {}

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        o.release();
    }

}
