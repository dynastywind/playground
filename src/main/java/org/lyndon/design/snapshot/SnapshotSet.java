package org.lyndon.design.snapshot;

import java.util.*;

public class SnapshotSet<T> {

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

    private long version = 0;
    private int tombstones;
    private List<Entry<T>> history = new ArrayList<>();
    private final Map<T, Entry<T>> live = new HashMap<>();
    private final TreeMap<Long, Integer> activeSnapshots = new TreeMap<>();

    public boolean add(T e) {
        Entry<T> cur = live.get(e);
        if (cur != null) {
            return false;
        }
        long v = ++version;
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
        cur.removeVer = ++version;
        tombstones++;
        compact();
        return true;
    }

    public boolean contains(T e) {
        return live.containsKey(e);
    }

    public int size() {
        return live.size();
    }

    private void compact() {
        if (activeSnapshots.isEmpty() && tombstones > history.size() / 2) {
            long minActive = minActiveVersion();
            List<Entry<T>> kept = new ArrayList<>(history.size() - tombstones + 8);
            int removed = 0;
            for (Entry<T> e : history) {
                if (!e.isLive() && e.removeVer <= minActive) {
                    removed++;
                    continue;
                }
                kept.add(e);
            }
            history = kept;
            tombstones -= removed;
        }
    }

    private long minActiveVersion() {
        return activeSnapshots.isEmpty() ? Long.MAX_VALUE : activeSnapshots.firstKey();
    }

    private void register(long v) {
        activeSnapshots.merge(v, 1, Integer::sum);
    }

    private void deregister(long v) {
        if (activeSnapshots.merge(v, -1, Integer::sum) <= 0) {
            activeSnapshots.remove(v);
        }
        compact();
    }

    public interface SnapshotIterator<T> extends Iterator<T>, AutoCloseable {
        @Override
        void close();
    }

    public SnapshotIterator<T> iterator() {
        final long snapshotVer = version;
        final int limit = history.size();
        register(snapshotVer);

        return new SnapshotIterator<>() {
            private int idx = 0;
            private Entry<T> next = null;
            private boolean isClosed = false;

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
                if (next == null) {
                    close();
                }
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

            @Override
            public void close() {
                if (!isClosed) {
                    isClosed = true;
                    deregister(snapshotVer);
                }
            }
        };
    }

}
