package org.lyndon.algo.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LC1110
 */
public class DeleteNodesAndReturnForest {

    /*
            1
         2      3
       n    n n     4
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        toDelete.add(-1);
        Set<TreeNode> candidates = new HashSet<>();
        TreeNode dummy = new TreeNode(-1);
        dummy.left = root;
        dfs(dummy, toDelete, candidates);
        return new ArrayList<>(candidates);
    }

    private void dfs(TreeNode node, Set<Integer> toDelete, Set<TreeNode> candidates) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            dfs(node.left, toDelete, candidates);
            if (toDelete.contains(node.left.val)) {
                node.left = null;
            } else if (toDelete.contains(node.val)) {
                candidates.add(node.left);
            }
        }
        if (node.right != null) {
            dfs(node.right, toDelete, candidates);
            if (toDelete.contains(node.right.val)) {
                node.right = null;
            } else if (toDelete.contains(node.val)) {
                candidates.add(node.right);
            }
        }
    }

}
