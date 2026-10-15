package org.lyndon.algo.tree;

/**
 * LC124
 */
public class BinaryTreeMaxPathSum {

    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        solve(root);
        return ans;
    }

    private int solve(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(solve(node.left), 0);
        int right = Math.max(solve(node.right), 0);

        int sum = node.val + left + right;
        ans = Math.max(ans, sum);
        return Math.max(left, right) + node.val;
    }

}
