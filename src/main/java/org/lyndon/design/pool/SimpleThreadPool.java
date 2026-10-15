package org.lyndon.design.pool;

import org.lyndon.design.queue.BoundedBlockingQueue;

import java.util.ArrayList;
import java.util.List;

public class SimpleThreadPool {

    private static final Runnable POISON = () -> {
    };

    private final BoundedBlockingQueue<Runnable> queue;

    private final List<Thread> workers = new ArrayList<>();

    private volatile boolean shutdown = false;

    public SimpleThreadPool(int nThreads, int queueCapacity) {
        this.queue = new BoundedBlockingQueue<>(queueCapacity);
        for (int i = 0; i < nThreads; i++) {
            // dangerous due to "this" escaping. Make sure no other initializing logic after t.start();
            Thread t = new Thread(this::runWorker, "worker-" + i);
            workers.add(t);
            t.start();
        }
    }

    public void submit(Runnable task) throws InterruptedException {
        if (shutdown) {
            throw new IllegalStateException("ThreadPool is shutdown");
        }
        queue.put(task);
    }

    public void shutdown() throws InterruptedException {
        shutdown = true;
        for (int i = 0; i < workers.size(); i++) {
            queue.put(POISON);
        }
    }

    public void shutdownNow() {
        shutdown = true;
        for (Thread t : workers) {
            t.interrupt();
        }
    }

    public void awaitTermination() throws InterruptedException {
        for (Thread t : workers) {
            t.join();
        }
    }

    private void runWorker() {
        try {
            while (true) {
                Runnable task = queue.take();
                if (task == POISON) {
                    return;
                }
                try {
                    task.run();
                } catch (Throwable t) {
                    System.err.println("task failed: " + t);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
