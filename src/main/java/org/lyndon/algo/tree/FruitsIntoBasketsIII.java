package org.lyndon.algo.tree;

/**
 * LC3479
 */
public class FruitsIntoBasketsIII {

    private static class SegmentTree {
        int n;
        int[] max;

        public SegmentTree(int[] baskets) {
            this.n = baskets.length;
            this.max = new int[n << 2];
            build(baskets, 1, 0, n - 1);
        }

        private void build(int[] baskets, int node, int l, int r) {
            if (l == r) {
                max[node] = baskets[l];
                return;
            }
            int mid = (l + r) >>> 1;
            build(baskets, node << 1, l, mid);
            build(baskets, node << 1 | 1, mid + 1, r);
            max[node] = Math.max(max[node << 1], max[node << 1 | 1]);
        }

        public void findAndUpdate(int target) {
            findAndUpdate(1, 0, n - 1, target);
        }

        private void findAndUpdate(int node, int l, int r, int target) {
            if (l == r) {
                max[node] = -1;
                return;
            }
            int mid = (l + r) >>> 1;
            if (max[node << 1] >= target) {
                findAndUpdate(node << 1, l, mid, target);
            } else {
                findAndUpdate(node << 1 | 1, mid + 1, r, target);
            }
            max[node] = Math.max(max[node << 1], max[node << 1 | 1]);
        }
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int ans = 0;
        for (int fruit : fruits) {
            if (tree.max[1] < fruit) {
                ans++;
                continue;
            }
            tree.findAndUpdate(fruit);
        }
        return ans;
    }

}
