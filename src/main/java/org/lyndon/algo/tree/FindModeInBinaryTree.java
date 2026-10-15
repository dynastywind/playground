package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LC501
 */
public class FindModeInBinaryTree {

    private int cnt = 0, maxCount = 0;
    private Integer prev;

    public int[] findMode(TreeNode root) {
        List<Integer> modes = new ArrayList<>();
        inorder(root, modes);
        return modes.stream().mapToInt(Integer::intValue).toArray();
    }

    private void inorder(TreeNode node, List<Integer> modes) {
        if (node == null) {
            return;
        }
        inorder(node.left, modes);
        cnt = (prev != null && prev == node.val ? cnt + 1 : 1);
        prev = node.val;
        if (cnt > maxCount) {
            maxCount = cnt;
            modes.clear();
            modes.add(node.val);
        } else if (cnt == maxCount) {
            modes.add(node.val);
        }
        inorder(node.right, modes);
    }

}
