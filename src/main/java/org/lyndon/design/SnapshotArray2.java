package org.lyndon.design;

import java.util.Arrays;
import java.util.Iterator;

public class SnapshotArray2 {

    private final int[][] ids;

    private final int[][] vals;

    private final int[] size;

    private int snapId = 0;

    public SnapshotArray2(int length) {
        this.ids = new int[length][];
        this.vals = new int[length][];
        this.size = new int[length];
    }

    public void set(int index, int value) {
        int n = size[index];
        if (n > 0 && ids[index][n - 1] == snapId) {
            vals[index][n - 1] = value;
            return;
        }

        ensureCapacity(index, n + 1);
        ids[index][n] = snapId;
        vals[index][n] = value;
        size[index]++;
    }

    private void ensureCapacity(int index, int need) {
        int len = ids[index] == null ? 0 : ids[index].length;
        if (len >= need) {
            return;
        }
        int newLen = Math.max(4, len << 1);
        ids[index] = (ids[index] == null ? new int[newLen] : Arrays.copyOf(ids[index], newLen));
        vals[index] = (vals[index] == null ? new int[newLen] : Arrays.copyOf(vals[index], newLen));
    }

    public int get(int index, int snapId) {
        int[] a = ids[index];
        int lo = 0;
        int hi = size[index];
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] <= snapId) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo == 0 ? 0 : vals[index][lo - 1];
    }

    public int snap() {
        return snapId++;
    }

    private final class SnapshotArrayIterator implements Iterator<Integer> {
        private final int snapId;
        private int i = 0;

        public SnapshotArrayIterator(int snapId) {
            this.snapId = snapId;
        }

        @Override
        public boolean hasNext() {
            return i < ids.length;
        }

        @Override
        public Integer next() {
            return 0;
        }

    }

}
