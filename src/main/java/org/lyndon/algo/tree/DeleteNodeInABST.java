package org.lyndon.algo.tree;

/**
 * LC450
 */
public class DeleteNodeInABST {

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = null, cur = root;
        while (cur != null && cur.val != key) {
            parent = cur;
            cur = key < cur.val ? cur.left : cur.right;
        }
        if (cur == null) {
            return root;
        }
        TreeNode node = delete(cur);
        if (parent == null) {
            return node;
        }
        if (parent.left == cur) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        return root;
    }

    private TreeNode delete(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }

        TreeNode parent = node, succ = node.right;
        while (succ.left != null) {
            parent = succ;
            succ = succ.left;
        }
        if (parent != node) {
            parent.left = succ.right;
            succ.right = node.right;
        }
        succ.left = node.left;
        return succ;
    }

}
