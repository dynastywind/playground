package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LC366
 */
public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> leaves = new ArrayList<>();
        dfs(root, leaves);
        return leaves;
    }

    private int dfs(TreeNode node, List<List<Integer>> leaves) {
        if (node == null) {
            return -1;
        }
        int leftHeight = dfs(node.left, leaves);
        int rightHeight = dfs(node.right, leaves);
        int height = Math.max(leftHeight, rightHeight) + 1;
        if (leaves.size() == height) {
            leaves.add(new ArrayList<>());
        }
        leaves.get(height).add(node.val);
        return height;
    }

}
