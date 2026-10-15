package org.lyndon.algo.tree;

import java.util.PriorityQueue;

/**
 * LC3691
 */
public class MaxTotalSubarrayValueII {

    private static class SegmentTree {
        private final int[] max;
        private final int[] min;
        private final int n;

        public SegmentTree(int[] nums) {
            this.n = nums.length;
            this.max = new int[n << 2];
            this.min = new int[n << 2];
            build(nums, 1, 0, n - 1);
        }

        private void build(int[] nums, int node, int l, int r) {
            if (l == r) {
                max[node] = nums[l];
                min[node] = nums[l];
                return;
            }
            int mid = (l + r) >>> 1;
            build(nums, node << 1, l, mid);
            build(nums, node << 1 | 1, mid + 1, r);
            pushUp(node);
        }

        private void pushUp(int node) {
            max[node] = Math.max(max[node << 1], max[node << 1 | 1]);
            min[node] = Math.min(min[node << 1], min[node << 1 | 1]);
        }

        public int queryValue(int ql, int qr) {
            long v = queryValue(1, 0, n - 1, ql, qr);
            return ((int) (v >> 32)) - (int) v;
        }

        private long queryValue(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) {
                return encode(max[node], min[node]);
            }
            int mid = (l + r) >>> 1;
            if (ql > mid) {
                return queryValue(node << 1 | 1, mid + 1, r, ql, qr);
            }
            if (qr <= mid) {
                return queryValue(node << 1, l, mid, ql, qr);
            }
            return merge(queryValue(node << 1, l, mid, ql, qr), queryValue(node << 1 | 1, mid + 1, r, ql, qr));
        }

        private long merge(long a, long b) {
            return encode(Math.max((int) (a >> 32), (int) (b >> 32)), Math.min((int) a, (int) b));
        }

        private long encode(int max, int min) {
            return ((long) max << 32) | (min & 0xFFFFFFFFL);
        }

    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{tree.queryValue(i, n - 1), i, n - 1});
        }
        long ans = 0;
        while (k-- > 0) {
            int[] cur = pq.poll();
            int v = cur[0];
            int l = cur[1];
            int r = cur[2];
            ans += v;
            if (r > l) {
                pq.offer(new int[]{tree.queryValue(l, r - 1), l, r - 1});
            }
        }
        return ans;
    }

}
