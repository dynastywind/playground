package org.lyndon.algo.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC662
 * Given the root of a binary tree, return the maximum width of the given tree.
 * <p>
 * The maximum width of a tree is the maximum width among all levels.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
 * <p>
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 */
public class MaxWidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        Queue<Object[]> queue = new LinkedList<>();
        queue.add(new Object[]{root, 1});
        int width = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Object[] head = queue.peek();
            int right = 0;
            for (int i = 0; i < size; i++) {
                Object[] cur = queue.poll();
                TreeNode node = (TreeNode) cur[0];
                int index = (int) cur[1];
                right = index;
                if (node.left != null) {
                    queue.add(new Object[]{node.left, 2 * index});
                }
                if (node.right != null) {
                    queue.add(new Object[]{node.right, 2 * index + 1});
                }
            }
            width = Math.max(width, right - (int) head[1] + 1);
        }
        return width;
    }

}
