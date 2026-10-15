package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * LC3161
 */
public class BlockPlacementQueries {

    private static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int n) {
            this.tree = new int[n << 2];
            this.n = n;
        }

        public void update(int i, int val) {
            update(1, 0, n - 1, i, val);
        }

        private void update(int node, int l, int r, int i, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }
            int mid = (l + r) >> 1;
            if (i <= mid) {
                update(node << 1, l, mid, i, val);
            } else {
                update(node << 1 | 1, mid + 1, r, i, val);
            }
            tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
        }

        public int query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        private int query(int node, int l, int r, int ql, int qr) {
            if (qr < l || ql > r) {
                return 0;
            }
            if (ql <= l && r <= qr) {
                return tree[node];
            }
            int mid = (l + r) >> 1;
            return Math.max(query(node << 1, l, mid, ql, qr), query(node << 1 | 1, mid + 1, r, ql, qr));
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int max = Integer.MIN_VALUE;
        for (int[] query : queries) {
            max = Math.max(max, query[1]);
        }
        SegmentTree tree = new SegmentTree(max + 1);
        List<Boolean> ans = new ArrayList<>();
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        for (int[] query : queries) {
            if (query[0] == 1) {
                int x = query[1];
                int pre = obstacles.lower(x);
                Integer next = obstacles.higher(x);
                obstacles.add(x);
                tree.update(x, x - pre);
                if (next != null) {
                    tree.update(next, next - x);
                }
            } else {
                int x = query[1];
                int size = query[2];
                int pre = obstacles.floor(x); // x might be an obstacle
                int len = Math.max(tree.query(0, x), x - pre);
                ans.add(len >= size);
            }
        }
        return ans;
    }

}
