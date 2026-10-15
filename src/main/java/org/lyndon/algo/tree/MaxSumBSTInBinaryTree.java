package org.lyndon.algo.tree;

/**
 * LC1373
 */
public class MaxSumBSTInBinaryTree {

    private static class Desc {
        int sum;
        boolean isBST;
        int min, max;

        Desc() {
            isBST = true;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
        }

        Desc(int sum, boolean isBST, int min, int max) {
            this.sum = sum;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    private int ans = 0;

    public int maxSumBST(TreeNode root) {
        ans = 0;
        solve(root);
        return ans;
    }

    private Desc solve(TreeNode node) {
        if (node == null) {
            return new Desc();
        }
        Desc left = solve(node.left);
        Desc right = solve(node.right);
        boolean isBST = left.isBST && right.isBST && left.max < node.val && right.min > node.val;
        if (!isBST) {
            return new Desc(0, false, 0, 0);
        }
        int sum = left.sum + right.sum + node.val;
        ans = Math.max(ans, sum);
        int min = (node.left == null) ? node.val : left.min;
        int max = (node.right == null) ? node.val : right.max;
        return new Desc(sum, true, min, max);
    }

    static void main() {
        var solution = new MaxSumBSTInBinaryTree();
        System.out.println(solution.maxSumBST(new TreeNode(1, new TreeNode(4, new TreeNode(2), new TreeNode(4)), new TreeNode(3, new TreeNode(2), new TreeNode(5, new TreeNode(4), new TreeNode(6))))));
        System.out.println(solution.maxSumBST(new TreeNode(4, new TreeNode(3, new TreeNode(1), new TreeNode(2)), null)));
        System.out.println(solution.maxSumBST(new TreeNode(-4, new TreeNode(-2), new TreeNode(-5))));
        System.out.println(solution.maxSumBST(new TreeNode(10, new TreeNode(5), new TreeNode(20))));
    }

}
