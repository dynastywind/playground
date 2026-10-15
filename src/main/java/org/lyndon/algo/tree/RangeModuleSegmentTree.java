package org.lyndon.algo.tree;

/**
 * LC715
 */
public class RangeModuleSegmentTree {

    private static final int LO = 1;
    private static final int HI = 1_000_000_000;

    private final Node root;

    private static class Node {
        boolean covered;
        Node left;
        Node right;
        int lazy; // 0: not set; 1: tracked; -1: not tracked
    }

    public RangeModuleSegmentTree() {
        this.root = new Node();
    }

    private void update(Node node, int low, int high, int l, int r, boolean val) {
        if (l > r) {
            return;
        }
        if (l <= low && high <= r) {
            node.covered = val;
            node.lazy = val ? 1 : -1;
            return;
        }
        pushDown(node);
        int mid = (low + high) >> 1;
        if (l <= mid) {
            update(node.left, low, mid, l, r, val);
        }
        if (mid < r) {
            update(node.right, mid + 1, high, l, r, val);
        }
        node.covered = node.left.covered && node.right.covered;
    }

    private boolean query(Node node, int low, int high, int ql, int qr) {
        if (ql > qr) {
            return true;
        }
        if (node.covered) {
            return true;
        }
        if (node.left == null) {
            return false;
        }
        pushDown(node);
        int mid = (low + high) >> 1;
        boolean covered = true;
        if (ql <= mid) {
            covered = query(node.left, low, mid, ql, qr);
        }
        if (covered && qr > mid) {
            covered = query(node.right, mid + 1, high, ql, qr);
        }
        return covered;
    }

    private void pushDown(Node node) {
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        if (node.lazy != 0) {
            boolean val = node.lazy == 1;
            node.left.covered = val;
            node.left.lazy = node.lazy;
            node.right.covered = val;
            node.right.lazy = node.lazy;
            node.lazy = 0;
        }
    }

    public void addRange(int left, int right) {
        update(root, LO, HI, left, right - 1, true);
    }

    public boolean queryRange(int left, int right) {
        return query(root, LO, HI, left, right - 1);
    }

    public void removeRange(int left, int right) {
        update(root, LO, HI, left, right - 1, false);
    }

}
