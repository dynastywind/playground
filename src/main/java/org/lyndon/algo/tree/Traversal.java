package org.lyndon.algo.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Traversal {

    public List<TreeNode> preorder(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        List<TreeNode> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

}
