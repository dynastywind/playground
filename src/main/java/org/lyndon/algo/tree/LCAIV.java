package org.lyndon.algo.tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LC1676
 */
public class LCAIV {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> set = new HashSet<>(Arrays.asList(nodes));
        return dfs(root, set);
    }

    private TreeNode dfs(TreeNode node, Set<TreeNode> set) {
        if (node == null) {
            return null;
        }
        if (set.contains(node)) {
            return node;
        }
        TreeNode left = dfs(node.left, set);
        TreeNode right = dfs(node.right, set);
        if (left != null && right != null) {
            return node;
        }
        return left == null ? right : left;
    }

}
