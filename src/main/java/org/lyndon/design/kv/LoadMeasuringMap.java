package org.lyndon.design.kv;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.LongAdder;

public class LoadMeasuringMap<K, V> {

    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();
    private final RateCounter putLoad = new RateCounter(10000);
    private final RateCounter getLoad = new RateCounter(10000);

    public void put(K key, V value) {
        map.put(key, value);
        putLoad.hit();
    }

    public V get(K key) {
        getLoad.hit();
        return map.get(key);
    }

    public double measurePutLoad() {
        return putLoad.ratePerSec();
    }

    public double measureGetLoad() {
        return getLoad.ratePerSec();
    }

    private static class RateCounter {
        private final int W;
        private final LongAdder[] buckets;
        private final AtomicLongArray bucketMillis;

        RateCounter(int windowMillis) {
            this.W = windowMillis;
            this.buckets = new LongAdder[W];
            this.bucketMillis = new AtomicLongArray(W);
            for (int i = 0; i < W; i++) {
                buckets[i] = new LongAdder();
                bucketMillis.set(i, -1);
            }
        }

        void hit() {
            long now = System.currentTimeMillis();
            int idx = (int) (now % W);
            long stored = bucketMillis.get(idx);
            if (stored != now && bucketMillis.compareAndSet(idx, stored, now)) {
                buckets[idx].reset();
            }
            buckets[idx].increment();
        }

        double ratePerSec() {
            long now = System.currentTimeMillis();
            long sum = 0;
            for (int i = 0; i < W; i++) {
                if (now - bucketMillis.get(i) < W) {
                    sum += buckets[i].sum();
                }
            }
            return sum * 1000.0 / W;
        }
    }

}
