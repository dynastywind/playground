package org.lyndon.design.cached;

import java.util.LinkedHashMap;
import java.util.Map;

public class CachedFiles {

    interface StorageClient {
        byte[] read(long offset, int length);   // 可能抛异常、可能很慢

        long size();
    }

    private static final int CHUNK_SIZE = 16;

    private final StorageClient storageClient;
    private final long fileSize;
    private final long capacityBytes;
    private final LinkedHashMap<Long, byte[]> cache;

    private long cachedBytes;

    public CachedFiles(StorageClient storageClient, long capacityBytes) {
        this.storageClient = storageClient;
        this.fileSize = storageClient.size();
        this.capacityBytes = capacityBytes;
        this.cache = new LinkedHashMap<>(16, 0.75f, true);
    }

    public byte[] read(long offset, int length) {
        if (offset < 0 || length < 0 || offset >= fileSize) {
            throw new IllegalArgumentException("offset or length out of range");
        }

        length = (int) Math.min(length, fileSize - offset);
        long firstChunk = offset / CHUNK_SIZE;
        // offset = 5, length = 11
        long lastChunk = (offset + length - 1) / CHUNK_SIZE;

        byte[] result = new byte[length];
        int pos = 0;
        for (long idx = firstChunk; idx <= lastChunk; idx++) {
            byte[] chunk = getChunk(idx);
            long chunkStart = idx * CHUNK_SIZE;
            int from = (int) Math.max(offset - chunkStart, 0);
            int to = (int) Math.min(offset + length - chunkStart, chunk.length);
            System.arraycopy(chunk, from, result, pos, to - from);
            pos += (to - from);
        }
        return result;
    }

    private byte[] getChunk(long idx) {
        byte[] cached = cache.get(idx);
        if (cached != null) {
            return cached;
        }
        byte[] data = storageClient.read(idx * CHUNK_SIZE, (int) Math.min(CHUNK_SIZE, fileSize - idx * CHUNK_SIZE));
        put(idx, data);
        return data;
    }

    private void put(long idx, byte[] data) {
        while (cachedBytes + data.length > capacityBytes && !cache.isEmpty()) {
            Map.Entry<Long, byte[]> toRemove = cache.firstEntry();
            cachedBytes -= toRemove.getValue().length;
            cache.remove(toRemove.getKey());
        }
        cache.put(idx, data);
        cachedBytes += data.length;
    }

}
