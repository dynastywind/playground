package org.lyndon.algo.tree;

/**
 * LC1650
 */
public class LCAIII {

    private static class Node extends TreeNode {
        Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p;
        Node b = q;
        while (a.val != b.val) {
            a = a.parent == null ? q : a.parent;
            b = b.parent == null ? p : b.parent;
        }
        return a;
    }

}
