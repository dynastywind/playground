package org.lyndon.algo.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC 1825 Hard
 * You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.
 * <p>
 * The MKAverage can be calculated using these steps:
 * <p>
 * If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
 * Remove the smallest k elements and the largest k elements from the container.
 * Calculate the average value for the rest of the elements rounded down to the nearest integer.
 * Implement the MKAverage class:
 * <p>
 * MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
 * void addElement(int num) Inserts a new element num into the stream.
 * int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.
 */
public class MKAverage {

    private static class BIT {
        private final int[] tree;

        public BIT(int n) {
            this.tree = new int[n + 1];
        }

        public void update(int node, int delta) {
            for (int i = node; i < tree.length; i += i & (-i)) {
                tree[i] += delta;
            }
        }

        public long query(int node) {
            long sum = 0;
            for (int i = node; i > 0; i -= i & (-i)) {
                sum += tree[i];
            }
            return sum;
        }

        public int[] getTree() {
            return tree;
        }
    }

    private static final int N = 100000;
    private final BIT sum;
    private final BIT cnt;
    private final Deque<Integer> queue;
    private final int m;
    private final int k;

    public MKAverage(int m, int k) {
        this.sum = new BIT(N + 1);
        this.cnt = new BIT(N + 1);
        this.queue = new ArrayDeque<>();
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        queue.add(num);
        cnt.update(num, 1);
        sum.update(num, num);
        if (queue.size() > m) {
            int old = queue.poll();
            cnt.update(old, -1);
            sum.update(old, -old);
        }
    }

    public int calculateMKAverage() {
        if (queue.size() < m) {
            return -1;
        }
        long middle = sumForSmallest(m - k) - sumForSmallest(k);
        return (int) (middle / (m - 2 * k));
    }

    private long sumForSmallest(int j) {
        if (j <= 0) {
            return 0;
        }
        int pos = 0;
        int count = 0;
        // max pos with count < j
        // pos + 1 is the first position that has count >= j
        // thus, pos + 1 is the target value
        for (int pw = Integer.highestOneBit(N); pw > 0; pw >>= 1) {
            int next = pw + pos;
            if (next <= N && count + cnt.getTree()[next] < j) {
                pos = next;
                count += cnt.getTree()[next];
            }
        }
        long s = sum.query(pos);
        int target = pos + 1;
        return s + (long) target * (j - count);
    }

}
