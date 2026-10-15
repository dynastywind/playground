package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LC987
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * <p>
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 * <p>
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 * <p>
 * Return the vertical order traversal of the binary tree.
 */
public class VerticalOrderTraversalOfBinaryTree {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);
        nodes.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });

        List<List<Integer>> ans = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;
        for (int[] node : nodes) {
            if (node[0] != prevCol) {
                prevCol = node[0];
                ans.add(new ArrayList<>());
            }
            ans.getLast().add(node[2]);
        }
        return ans;
    }

    private void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(new int[]{col, row, node.val});
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }

}
