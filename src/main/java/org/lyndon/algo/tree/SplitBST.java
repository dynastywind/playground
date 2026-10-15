package org.lyndon.algo.tree;

import org.lyndon.utils.PrintUtils;

/**
 * LC776
 */
public class SplitBST {

    public TreeNode[] splitBST(TreeNode root, int target) {
        TreeNode small = new TreeNode();
        TreeNode large = new TreeNode();
        TreeNode s = small, l = large;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val <= target) {
                s.right = cur;
                s = cur;
                TreeNode next = cur.right;
                cur.right = null;
                cur = next;
            } else {
                l.left = cur;
                l = cur;
                TreeNode next = cur.left;
                cur.left = null;
                cur = next;
            }
        }
        return new TreeNode[]{small.right, large.left};
    }

    public TreeNode[] splitBST2(TreeNode root, int target) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }
        if (root.val <= target) {
            TreeNode[] res = splitBST2(root.right, target);
            root.right = res[0];
            return new TreeNode[]{root, res[1]};
        } else {
            TreeNode[] res = splitBST2(root.left, target);
            root.left = res[1];
            return new TreeNode[]{res[0], root};
        }
    }

    static void main() {
        var solution = new SplitBST();
//        PrintUtils.printArray(solution.splitBST(new TreeNode(1), 1));
//        PrintUtils.printArray(solution.splitBST(new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(6, new TreeNode(5), new TreeNode(7))), 2));
        PrintUtils.printArray(solution.splitBST(new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), null), 6));
    }

}
