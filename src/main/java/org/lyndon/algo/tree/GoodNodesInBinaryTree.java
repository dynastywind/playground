package org.lyndon.algo.tree;

/**
 * LC1448
 */
public class GoodNodesInBinaryTree {

    private int ans;

    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return ans;
    }

    private void dfs(TreeNode node, int max) {
        if (node == null) {
            return;
        }
        if (node.val >= max) {
            ans++;
        }
        max = Math.max(max, node.val);
        dfs(node.left, max);
        dfs(node.right, max);
    }

}
