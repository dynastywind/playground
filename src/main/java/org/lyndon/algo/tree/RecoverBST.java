package org.lyndon.algo.tree;

/**
 * LC99
 */
public class RecoverBST {

    private TreeNode prev, first, second;

    public void recoverTree(TreeNode root) {
        solve(root);
        swap(first, second);
    }

    private void solve(TreeNode node) {
        if (node == null) {
            return;
        }
        solve(node.left);
        if (prev != null && prev.val > node.val) {
            if (first == null) {
                first = prev;
            }
            second = node;
        }
        prev = node;
        solve(node.right);
    }

    private void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    static void main() {
        var solution = new RecoverBST();
        TreeNode root = new TreeNode(2, new TreeNode(3), new TreeNode(1));
        solution.recoverTree(root);
        System.out.println(root);
    }

}
