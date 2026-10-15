package org.lyndon.algo.graph.eulerian;

import org.lyndon.algo.tree.TreeNode;

/**
 * LC2458
 * You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.
 * <p>
 * You have to perform m independent queries on the tree where in the ith query you do the following:
 * <p>
 * Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
 * Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
 * <p>
 * Note:
 * <p>
 * The queries are independent, so the tree returns to its initial state after each query.
 * The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 */
public class HeightOfBinaryTreeAfterSubTreeRemoval {

    private int maxDepth;

    // 1 3 2 4 6 5 7
    // 1 4 5 7 6 3 2
    // 5 8 2 4 6 1 9 3 7
    // 5 9 7 3 8 1 2 6 4
    public int[] treeQueries(TreeNode root, int[] queries) {
        int[] heights = new int[100001];
        maxDepth = 0;
        preOrder(root, 0, heights, true);
        maxDepth = 0;
        preOrder(root, 0, heights, false);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = heights[queries[i]];
        }
        return ans;
    }

    private void preOrder(TreeNode node, int depth, int[] heights, boolean left) {
        if (node == null) {
            return;
        }
        heights[node.val] = Math.max(heights[node.val], maxDepth);
        maxDepth = Math.max(maxDepth, depth);

        if (left) {
            preOrder(node.left, depth + 1, heights, true);
            preOrder(node.right, depth + 1, heights, true);
        } else {
            preOrder(node.right, depth + 1, heights, false);
            preOrder(node.left, depth + 1, heights, false);
        }
    }

}
