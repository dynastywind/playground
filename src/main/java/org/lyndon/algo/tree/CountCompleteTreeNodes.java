package org.lyndon.algo.tree;

/**
 * LC222
 */
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (left == right) {
            return (1 << left) + countNodes(root.right);
        }
        return countNodes(root.left) + (1 << right);
    }

    private int getDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            node = node.left;
            depth++;
        }
        return depth;
    }

}
