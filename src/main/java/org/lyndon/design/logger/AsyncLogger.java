package org.lyndon.design.logger;

import org.lyndon.design.queue.BoundedBlockingQueue;

import java.io.Writer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class AsyncLogger {

    private static final String POISON = "__POISON__";

    private final Thread writer;

    private final AtomicLong dropped = new AtomicLong(0);

    private final BoundedBlockingQueue<String> queue = new BoundedBlockingQueue<>(10000);

    public AsyncLogger(Writer output) {
        this.writer = new Thread(() -> {
            try {
                while (true) {
                    String msg = queue.take();
                    if (msg == POISON) {
                        break;
                    }
                    output.write(msg);
                    output.write('\n');
                }
                output.flush();
            } catch (Exception e) {
                // ignore
            }
        }, "log-writer");
        this.writer.start();
    }

    public void log(String msg) {
        if (!queue.offer(msg)) {
            dropped.incrementAndGet();
        }
    }

    public void close() throws Exception {
        if (!queue.offer(POISON, 5, TimeUnit.SECONDS)) {
            writer.interrupt();
        }
        writer.join(10000);
    }

}
