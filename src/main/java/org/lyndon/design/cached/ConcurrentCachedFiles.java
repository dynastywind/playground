package org.lyndon.design.cached;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentCachedFiles {

    private static final int CHUNK_SIZE = 16;

    private final CachedFiles.StorageClient storageClient;
    private final long fileSize;
    private final long capacityBytes;

    private final Object lock;
    private final LinkedHashMap<Long, byte[]> cache;

    private final ExecutorService pool;
    private final ConcurrentHashMap<Long, CompletableFuture<byte[]>> inFlights;

    private long cachedBytes;

    public ConcurrentCachedFiles(CachedFiles.StorageClient storageClient, long capacityBytes, int parallelism) {
        this.storageClient = storageClient;
        this.fileSize = storageClient.size();
        this.capacityBytes = capacityBytes;
        this.lock = new Object();
        this.cache = new LinkedHashMap<>(16, 0.75f, true);
        this.pool = Executors.newFixedThreadPool(parallelism);
        this.inFlights = new ConcurrentHashMap<>();
    }

    public byte[] read(long offset, int length) {
        if (offset < 0 || length < 0 || offset >= fileSize) {
            throw new IllegalArgumentException("offset or length out of range");
        }

        length = (int) Math.min(length, fileSize - offset);
        long firstChunk = offset / CHUNK_SIZE;
        // offset = 5, length = 11
        long lastChunk = (offset + length - 1) / CHUNK_SIZE;

        Map<Long, CompletableFuture<byte[]>> futures = new LinkedHashMap<>();
        for (long idx = firstChunk; idx <= lastChunk; idx++) {
            futures.put(idx, getChunkAsync(idx));
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (Map.Entry<Long, CompletableFuture<byte[]>> entry : futures.entrySet()) {
            byte[] chunk = entry.getValue().join();
            long chunkStart = entry.getKey() * CHUNK_SIZE;
            int from = (int) Math.max(offset - chunkStart, 0);
            int to = (int) Math.min(offset + length - chunkStart, chunk.length);
            System.arraycopy(chunk, from, result, pos, to - from);
            pos += (to - from);
        }
        return result;
    }

    private CompletableFuture<byte[]> getChunkAsync(long idx) {
        byte[] cached = lookup(idx);
        if (cached != null) {
            return CompletableFuture.completedFuture(cached);
        }

        return inFlights.computeIfAbsent(idx, k -> CompletableFuture.supplyAsync(() -> {
            byte[] b = lookup(idx);
            if (b != null) {
                return b;
            }
            int len = (int) Math.min(CHUNK_SIZE, fileSize - k * CHUNK_SIZE);
            byte[] data = storageClient.read(k * CHUNK_SIZE, len);
            store(k, data);
            return data;
        }, pool).whenComplete((v, e) -> inFlights.remove(k)));
    }

    private byte[] lookup(long idx) {
        synchronized (lock) {
            return cache.get(idx);
        }
    }

    private void store(long idx, byte[] data) {
        synchronized (lock) {
            while (cachedBytes + data.length > capacityBytes) {
                Map.Entry<Long, byte[]> entry = cache.firstEntry();
                cachedBytes -= entry.getValue().length;
                cache.remove(entry.getKey());
            }
            cache.put(idx, data);
            cachedBytes += data.length;
        }
    }

}
