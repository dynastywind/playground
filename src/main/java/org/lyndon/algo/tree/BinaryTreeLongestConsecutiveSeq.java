package org.lyndon.algo.tree;

/**
 * LC298
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 * <p>
 * A consecutive sequence path is a path where the values increase by one along the path.
 * <p>
 * Note that the path can start at any node in the tree, and you cannot go from a node to its parent in the path.
 */
public class BinaryTreeLongestConsecutiveSeq {

    private int ans = 1;

    public int longestConsecutive(TreeNode root) {
        dfs(root, 1);
        return ans;
    }

    private void dfs(TreeNode node, int cur) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            if (node.left.val == node.val + 1) {
                dfs(node.left, cur + 1);
            } else {
                dfs(node.left, 1);
            }
        }
        if (node.right != null) {
            if (node.right.val == node.val + 1) {
                dfs(node.right, cur + 1);
            } else {
                dfs(node.right, 1);
            }
        }
        ans = Math.max(ans, cur);
    }

}
