package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LC742
 * Given the root of a binary tree where every node has a unique value and a target integer k, return the value of the nearest leaf node to the target k in the tree.
 * <p>
 * Nearest to a leaf
 */
public class ClosestLeaf {

    public int findClosestLeaf(TreeNode root, int k) {
        List<int[]> candidates = new ArrayList<>();
        dfs(root, k, candidates);
        int min = Integer.MAX_VALUE;
        int maxNode = -1;
        for (int[] candidate : candidates) {
            if (candidate[1] < min) {
                min = candidate[1];
                maxNode = candidate[0];
            }
        }
        return maxNode;
    }

    private int dfs(TreeNode node, int target, List<int[]> candidates) {
        if (node == null) {
            return -1;
        }
        if (node.val == target) {
            candidates.add(collect(node, 0));
            return 0;
        }
        int left = dfs(node.left, target, candidates);
        if (left >= 0) {
            if (node.right != null) {
                candidates.add(collect(node.right, left + 1));
            }
            return left + 1;
        }
        int right = dfs(node.right, target, candidates);
        if (right >= 0) {
            if (node.left != null) {
                candidates.add(collect(node.left, right + 1));
            }
            return right + 1;
        }
        return -1;
    }

    private int[] collect(TreeNode node, int distance) {
        if (node.left == null && node.right == null) {
            return new int[]{node.val, distance};
        }
        int[] ans = new int[]{-1, Integer.MAX_VALUE};
        if (node.left != null) {
            ans = collect(node.left, distance + 1);
        }
        if (node.right != null) {
            int[] r = collect(node.right, distance + 1);
            if (r[1] < ans[1]) {
                ans = r;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new ClosestLeaf();
        System.out.println(solution.findClosestLeaf(new TreeNode(1), 1));
        System.out.println(solution.findClosestLeaf(new TreeNode(1, new TreeNode(3), new TreeNode(2)), 1));
    }

}
