package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LC1382
 * Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
 * <p>
 * A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
 */
public class BalanceBST {

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        inorder(root, vals);
        return build(vals, 0, vals.size() - 1);
    }

    private void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) {
            return;
        }
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }

    private TreeNode build(List<Integer> vals, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) >>> 1;
        TreeNode node = new TreeNode(vals.get(mid));
        node.left = build(vals, low, mid - 1);
        node.right = build(vals, mid + 1, high);
        return node;
    }

}
