package org.lyndon.algo.tree;

/**
 * LC968
 */
public class BinaryTreeCameras {

    private int cameras = 0;

    public int minCameraCover(TreeNode root) {
        int[] dp = dfs(root);
        return Math.min(dp[0], dp[1]);
    }

    // 0 = not covered
    // 1 = covered without camera
    // 2 = covered with camera
    private int solve(TreeNode node) {
        if (node == null) {
            return 1;
        }
        int left = solve(node.left);
        int right = solve(node.right);
        if (left == 0 || right == 0) {
            cameras++;
            return 2;
        }
        if (left == 2 || right == 2) {
            return 1;
        }
        return 0;
    }

    //[with camera, covered without camera, not covered without camera]
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int put = Math.min(left[0], Math.min(left[1], left[2])) + Math.min(right[0], Math.min(right[1], right[2])) + 1;
        int covered = Math.min(left[0] + Math.min(right[0], right[1]), right[0] + Math.min(left[0], left[1]));
        int uncovered = left[1] + right[1];
        return new int[]{put, covered, uncovered};
    }

    static void main() {
        var solution = new BinaryTreeCameras();
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), null);
        System.out.println(solution.minCameraCover(root));
        TreeNode root2 = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4, null, new TreeNode(5)), null), null), null);
        System.out.println(solution.minCameraCover(root2));
    }

}
