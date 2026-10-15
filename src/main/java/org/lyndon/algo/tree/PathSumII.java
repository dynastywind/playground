package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LC113
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int rem, List<Integer> path, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.left == null && node.right == null) {
            if (node.val == rem) {
                result.add(new ArrayList<>(path));
            }
        }
        if (node.left != null) {
            dfs(node.left, rem - node.val, path, result);
        }
        if (node.right != null) {
            dfs(node.right, rem - node.val, path, result);
        }
        path.removeLast();
    }

}
