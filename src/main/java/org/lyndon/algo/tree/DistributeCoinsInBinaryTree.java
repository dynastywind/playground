package org.lyndon.algo.tree;

/**
 * LC979
 */
public class DistributeCoinsInBinaryTree {

    private int moves;

    public int distributeCoins(TreeNode root) {
        moves = 0;
        dfs(root);
        return moves;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        moves += Math.abs(left) + Math.abs(right);
        return node.val - 1 + left + right;
    }

}
