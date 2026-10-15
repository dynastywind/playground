package org.lyndon.algo.tree;

/**
 * LC701
 */
public class InsertIntoABST {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        solve(root, val);
        return root;
    }

    private void solve(TreeNode node, int val) {
        if (node.val > val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
            } else {
                solve(node.left, val);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(val);
            } else {
                solve(node.right, val);
            }
        }
    }

}
