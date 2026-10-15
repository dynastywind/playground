package org.lyndon.algo.tree;

/**
 * LC549
 * Given the root of a binary tree, return the length of the longest consecutive path in the tree.
 * <p>
 * A consecutive path is a path where the values of the consecutive nodes in the path differ by one. This path can be either increasing or decreasing.
 * <p>
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
 * On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 */
public class BinaryTreeLongestConsecutiveSeqII {

    private int ans = 1;

    /*
                        4
                  -7            -3
                        -9              -3
                     9      -7       -4
                 6        -6   -6
               0    6    5    9
                -1 -4       -2

     */
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int inc = 1, dec = 1;
        if (node.left != null) {
            int[] left = dfs(node.left);
            if (node.left.val - node.val == 1) {
                inc = left[0] + 1;
            } else if (node.val - node.left.val == 1) {
                dec = left[1] + 1;
            }
        }
        if (node.right != null) {
            int[] right = dfs(node.right);
            if (node.right.val - node.val == 1) {
                inc = Math.max(inc, right[0] + 1);
            } else if (node.val - node.right.val == 1) {
                dec = Math.max(dec, right[1] + 1);
            }
        }
        ans = Math.max(ans, inc + dec - 1);
        return new int[]{inc, dec};
    }

}
