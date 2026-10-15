package org.lyndon.design.snapshot;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentSnapshotSet<T> {

    private static class Entry<T> {
        final T value;
        final long addVer;
        long removeVer = Long.MAX_VALUE;

        Entry(T value, long addVer) {
            this.value = value;
            this.addVer = addVer;
        }

        boolean visibleAt(long v) {
            return addVer <= v && v < removeVer;
        }

        boolean isLive() {
            return removeVer == Long.MAX_VALUE;
        }
    }

    private final AtomicLong version = new AtomicLong(0);
    private final Map<T, Entry<T>> live = new ConcurrentHashMap<>();
    private final List<Entry<T>> history = new CopyOnWriteArrayList<>();

    public boolean add(T e) {
        Entry<T> cur = live.get(e);
        if (cur != null) {
            return false;
        }
        long v = version.incrementAndGet();
        Entry<T> entry = new Entry<>(e, v);
        live.put(e, entry);
        history.add(entry);
        return true;
    }

    public boolean remove(T e) {
        Entry<T> cur = live.remove(e);
        if (cur == null) {
            return false;
        }
        cur.removeVer = version.incrementAndGet();
        return true;
    }

    public boolean contains(T e) {
        return live.containsKey(e);
    }

    public int size() {
        return live.size();
    }

    public Iterator<T> iterator() {
        final long snapshotVer = version.get();
        final int limit = history.size();

        return new Iterator<>() {
            private int idx = 0;
            private Entry<T> next = null;

            private void advance() {
                while (next == null && idx < limit) {
                    Entry<T> e = history.get(idx++);
                    if (e.visibleAt(snapshotVer)) {
                        next = e;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                advance();
                return next != null;
            }

            @Override
            public T next() {
                advance();
                if (next == null) {
                    throw new NoSuchElementException();
                }
                T v = next.value;
                next = null;
                return v;
            }
        };
    }

}
