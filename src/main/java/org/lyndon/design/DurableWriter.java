package org.lyndon.design;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class DurableWriter implements AutoCloseable {

    @NoArgsConstructor
    private static class PendingWrite {
        byte[] data;
        CountDownLatch done;
        Exception error;

        PendingWrite(byte[] data, CountDownLatch done) {
            this.data = data;
            this.done = done;
        }
    }

    private final RandomAccessFile file;
    private final FileChannel channel;
    private final BlockingQueue<PendingWrite> queue;
    private final Thread writeThread;

    private volatile boolean running;

    public DurableWriter(String path, int capacity) throws IOException {
        this.file = new RandomAccessFile(path, "rw");
        this.channel = file.getChannel();
        // append
        this.channel.position(channel.size());

        this.queue = new ArrayBlockingQueue<>(capacity);
        this.running = true;
        this.writeThread = new Thread(() -> {
            while (running) {
                write();
            }
        });
        this.writeThread.start();
    }

    public void append(byte[] data) throws InterruptedException {
        PendingWrite pendingWrite = new PendingWrite(data, new CountDownLatch(1));
        queue.put(pendingWrite);
        pendingWrite.done.await();
    }

    private void write() {
        List<PendingWrite> batch = new ArrayList<>();
        try {
            batch.add(queue.take());
            queue.drainTo(batch);
            flushBatch(batch);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (IOException e) {
            for (PendingWrite pendingWrite : batch) {
                pendingWrite.error = e;
            }
        } finally {
            for (PendingWrite pendingWrite : batch) {
                pendingWrite.done.countDown();
            }
        }
    }

    private void flushBatch(List<PendingWrite> batch) throws IOException {
        ByteBuffer[] buffers = new ByteBuffer[batch.size()];
        long total = 0;
        for (int i = 0; i < batch.size(); i++) {
            total += batch.get(i).data.length;
            buffers[i] = ByteBuffer.wrap(batch.get(i).data);
        }
        long written = 0;
        while (written < total) {
            written += channel.write(buffers);
        }
        channel.force(false);
    }

    @Override
    public void close() throws IOException {
        if (!running) {
            return;
        }
        running = false;
        writeThread.interrupt();
        try {
            writeThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        channel.force(true);
        channel.close();
        file.close();
    }

}
