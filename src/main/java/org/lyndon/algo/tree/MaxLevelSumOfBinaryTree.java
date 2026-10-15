package org.lyndon.algo.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LC1161
 */
public class MaxLevelSumOfBinaryTree {

    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int best = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (sum > best) {
                best = sum;
                ans = level;
            }
            level++;
        }
        return ans;
    }

    static void main() {
        var solution = new MaxLevelSumOfBinaryTree();
        System.out.println(solution.maxLevelSum(new TreeNode(1, new TreeNode(7, new TreeNode(7), new TreeNode(-8)), new TreeNode(0))));
        System.out.println(solution.maxLevelSum(new TreeNode(1, new TreeNode(1, new TreeNode(7), new TreeNode(-8)), new TreeNode(0))));
    }

}
