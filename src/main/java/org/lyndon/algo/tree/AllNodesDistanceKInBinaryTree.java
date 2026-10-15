package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LC863
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
 * <p>
 * You can return the answer in any order.
 */
public class AllNodesDistanceKInBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();
        dfs(root, target, result, k);
        return result;
    }

    private int dfs(TreeNode node, TreeNode target, List<Integer> nodes, int k) {
        if (node == null) {
            return -1;
        }
        if (node == target) {
            collectChildren(node, nodes, 0, k);
            return 1;
        }
        int left = dfs(node.left, target, nodes, k);
        if (left > 0) {
            if (left == k) {
                nodes.add(node.val);
            }
            collectChildren(node.right, nodes, left + 1, k);
            return left + 1;
        }
        int right = dfs(node.right, target, nodes, k);
        if (right > 0) {
            if (right == k) {
                nodes.add(node.val);
            }
            collectChildren(node.left, nodes, right + 1, k);
            return right + 1;
        }
        return -1;
    }

    private void collectChildren(TreeNode node, List<Integer> nodes, int d, int k) {
        if (node == null || d > k) {
            return;
        }
        if (d == k) {
            nodes.add(node.val);
            return;
        }
        collectChildren(node.left, nodes, d + 1, k);
        collectChildren(node.right, nodes, d + 1, k);
    }

}
