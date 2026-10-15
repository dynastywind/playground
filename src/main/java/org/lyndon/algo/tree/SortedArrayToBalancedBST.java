package org.lyndon.algo.tree;

/**
 * LC108
 */
public class SortedArrayToBalancedBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        return solve(nums, 0, n - 1);
    }

    private TreeNode solve(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = solve(nums, start, mid - 1);
        root.right = solve(nums, mid + 1, end);
        return root;
    }

    static void main() {
        var solution = new SortedArrayToBalancedBST();
        System.out.println(solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
    }

}
