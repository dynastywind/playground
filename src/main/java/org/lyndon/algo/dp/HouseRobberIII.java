package org.lyndon.algo.dp;

import org.lyndon.algo.tree.TreeNode;

/**
 * LC337
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 * <p>
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {
        return dfs(root)[0];
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int nodeValue = Math.max(left[0] + right[0], node.val + left[1] + right[1]);
        return new int[]{nodeValue, left[0] + right[0]};
    }

    public static void main(String[] args) {
        var solution = new HouseRobberIII();
        TreeNode c1 = new TreeNode(3);
        TreeNode c2 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2, null, c1);
        TreeNode b2 = new TreeNode(3, null, c2);
        TreeNode root = new TreeNode(3, b1, b2);
        System.out.println(solution.rob(root));
    }

}
