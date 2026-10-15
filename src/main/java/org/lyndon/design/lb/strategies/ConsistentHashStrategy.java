package org.lyndon.design.lb.strategies;

import org.lyndon.design.lb.Backend;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class ConsistentHashStrategy implements RoutingStrategy {

    private final int vnodesPerWeight;
    private final RoutingStrategy fallback;        // key 为 null 时降级
    private final ReentrantLock lock = new ReentrantLock();
    private volatile Ring ring = new Ring(null, Collections.emptyNavigableMap());

    private record Ring(List<Backend> source, NavigableMap<Long, String> slots) {
    }

    public ConsistentHashStrategy(int vnodesPerWeight, RoutingStrategy fallback) {
        if (vnodesPerWeight <= 0) throw new IllegalArgumentException("vnodes must be > 0");
        this.vnodesPerWeight = vnodesPerWeight;
        this.fallback = Objects.requireNonNull(fallback);
    }

    @Override
    public String select(List<Backend> backends, String key) {
        if (key == null) return fallback.select(backends, null);

        Ring r = ring;
        if (r.source() != backends) r = rebuild(backends);      // 引用比较，非 equals

        long h = hash64(key);
        var e = r.slots().ceilingEntry(h);
        return (e != null ? e : r.slots().firstEntry()).getValue();   // 环形回绕
    }

    private Ring rebuild(List<Backend> backends) {
        lock.lock();
        try {
            Ring cur = ring;
            if (cur.source() == backends) return cur;           // 双检，避免重复建环
            NavigableMap<Long, String> slots = new TreeMap<>();
            for (Backend b : backends) {
                int v = vnodesPerWeight * b.weight();           // 权重 → 虚节点数
                for (int i = 0; i < v; i++)
                    slots.put(hash64(b.id() + '#' + i), b.id());
            }
            Ring built = new Ring(backends, Collections.unmodifiableNavigableMap(slots));
            ring = built;
            return built;
        } finally {
            lock.unlock();
        }
    }

    /**
     * FNV-1a 64 + 雪崩混淆。生产建议换 Guava Murmur3_128 或 xxHash。
     */
    private static long hash64(String s) {
        long h = 0xcbf29ce484222325L;
        for (byte b : s.getBytes(StandardCharsets.UTF_8)) {
            h ^= (b & 0xff);
            h *= 0x100000001b3L;
        }
        h ^= h >>> 33;
        h *= 0xff51afd7ed558ccdL;
        h ^= h >>> 33;
        h *= 0xc4ceb9fe1a85ec53L;
        return h ^ (h >>> 33);
    }

}
