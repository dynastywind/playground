package org.lyndon.algo.tree;

/**
 * LC1483
 * You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of ith node. The root of the tree is node 0. Find the kth ancestor of a given node.
 * <p>
 * The kth ancestor of a tree node is the kth node in the path from that node to the root node.
 * <p>
 * Implement the TreeAncestor class:
 * <p>
 * TreeAncestor(int n, int[] parent) Initializes the object with the number of nodes in the tree and the parent array.
 * int getKthAncestor(int node, int k) return the kth ancestor of the given node node. If there is no such ancestor, return -1.
 */
public class TreeAncestor {

    private final int LOG;

    // up[i][j] = i'th 2^j'th parent
    private final int[][] up;

    public TreeAncestor(int n, int[] parent) {
        int log = 1;
        while ((1 << log) <= n) {
            log++;
        }
        this.LOG = log;

        this.up = new int[n][LOG];
        for (int i = 0; i < n; i++) {
            up[i][0] = parent[i];
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int mid = up[i][j - 1];
                up[i][j] = mid < 0 ? -1 : up[mid][j - 1];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int cur = node;
        for (int j = LOG - 1; j >= 0 && cur != -1; j--) {
            if (k >= (1 << j)) {
                cur = up[cur][j];
                k -= (1 << j);
            }
        }
        return cur;
    }

}
