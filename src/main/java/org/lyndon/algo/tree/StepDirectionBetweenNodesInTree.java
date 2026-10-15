package org.lyndon.algo.tree;

/**
 * LC2096
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 * <p>
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 * <p>
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t
 */
public class StepDirectionBetweenNodesInTree {

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder start = new StringBuilder();
        dfs(root, startValue, start);
        StringBuilder end = new StringBuilder();
        dfs(root, destValue, end);
        String pathA = start.toString();
        String pathB = end.toString();
        int i = 0;
        while (i < pathA.length() && i < pathB.length()) {
            if (pathA.charAt(i) != pathB.charAt(i)) {
                break;
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < pathA.length(); j++) {
            sb.append('U');
        }
        for (int j = i; j < pathB.length(); j++) {
            sb.append(pathB.charAt(j));
        }
        return sb.toString();
    }

    private boolean dfs(TreeNode node, int target, StringBuilder sb) {
        if (node == null) {
            return false;
        }
        if (node.val == target) {
            return true;
        }
        sb.append('L');
        if (dfs(node.left, target, sb)) {
            return true;
        }
        sb.setCharAt(sb.length() - 1, 'R');
        if (dfs(node.right, target, sb)) {
            return true;
        }
        sb.setLength(sb.length() - 1);
        return false;
    }

    public static void main(String[] args) {
        var solution = new StepDirectionBetweenNodesInTree();
        TreeNode three = new TreeNode(3);
        TreeNode six = new TreeNode(6);
        TreeNode four = new TreeNode(4);
        TreeNode one = new TreeNode(1, three, null);
        TreeNode two = new TreeNode(2, six, four);
        TreeNode five = new TreeNode(5, one, two);
        System.out.println(solution.getDirections(five, 3, 6));
    }

}
