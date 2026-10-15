package org.lyndon.algo.tree;

/**
 * LC235
 */
public class LCAOfBST {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }
        if (root.val > p.val && root.val < q.val) {
            return root;
        }
        if (root.val == p.val) {
            return root;
        }
        if (root.val == q.val) {
            return root;
        }
        if (root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return lowestCommonAncestor(root.right, p, q);
    }

}
