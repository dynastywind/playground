package org.lyndon.design;

import java.util.*;

/**
 * LC3369
 */
public class StatisticsTracker {

    private static class MultiSet {
        private final TreeMap<Integer, Integer> map = new TreeMap<>();
        private int size = 0;

        void add(int x) {
            map.merge(x, 1, Integer::sum);
            size++;
        }

        void remove(int x) {
            if (!map.containsKey(x)) {
                return;
            }
            if (map.merge(x, -1, Integer::sum) == 0) {
                map.remove(x);
            }
            size--;
        }

        int min() {
            return map.firstKey();
        }

        int max() {
            return map.lastKey();
        }

        int size() {
            return size;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    private final Queue<Integer> queue;
    private final MultiSet lo;
    private final MultiSet hi;
    private final Map<Integer, Integer> freq;
    private final TreeMap<Integer, TreeSet<Integer>> mode;

    private long sum = 0;

    public StatisticsTracker() {
        this.queue = new ArrayDeque<>();
        this.lo = new MultiSet();
        this.hi = new MultiSet();
        this.freq = new HashMap<>();
        this.mode = new TreeMap<>();
    }

    public void addNumber(int number) {
        queue.offer(number);
        sum += number;

        if (!lo.isEmpty() && number <= lo.max()) {
            lo.add(number);
        } else {
            hi.add(number);
        }
        rebalance();
        updateFreq(number, 1);
    }

    public void removeFirstAddedNumber() {
        if (queue.isEmpty()) {
            return;
        }

        int x = queue.poll();
        sum -= x;

        if (!lo.isEmpty() && x <= lo.max()) {
            lo.remove(x);
        } else {
            hi.remove(x);
        }
        rebalance();
        updateFreq(x, -1);
    }

    public int getMean() {
        return (int) (sum / queue.size());
    }

    public int getMedian() {
        return hi.min();
    }

    public int getMode() {
        return mode.lastEntry().getValue().first();
    }

    private void rebalance() {
        if (hi.size() > lo.size() + 1) {
            int x = hi.min();
            hi.remove(x);
            lo.add(x);
        } else if (lo.size() > hi.size()) {
            int x = lo.max();
            lo.remove(x);
            hi.add(x);
        }
    }

    private void updateFreq(int x, int delta) {
        int old = freq.getOrDefault(x, 0);
        if (old > 0) {
            TreeSet<Integer> xs = mode.get(old);
            xs.remove(x);
            if (xs.isEmpty()) {
                mode.remove(old);
            }
        }
        int now = old + delta;
        if (now > 0) {
            freq.put(x, now);
            mode.computeIfAbsent(now, k -> new TreeSet<>()).add(x);
        } else {
            freq.remove(x);
        }
    }

}
