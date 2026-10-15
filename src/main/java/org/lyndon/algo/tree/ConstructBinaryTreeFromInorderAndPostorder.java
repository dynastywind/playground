package org.lyndon.algo.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * LC106
 */
public class ConstructBinaryTreeFromInorderAndPostorder {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int l = inorder.length;
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < l; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(0, l - 1, 0, l - 1, postorder, inorderMap);
    }

    private TreeNode build(int i, int j, int m, int n, int[] postorder, Map<Integer, Integer> inorderMap) {
        int rootVal = postorder[n];
        TreeNode root = new TreeNode(rootVal);
        int mid = inorderMap.get(rootVal);
        int leftLen = mid - i;

        if (mid > i) {
            root.left = build(i, mid - 1, m, m + leftLen - 1, postorder, inorderMap);
        }
        if (mid < j) {
            root.right = build(mid + 1, j, m + leftLen, n - 1, postorder, inorderMap);
        }
        return root;
    }

    public static void main(String[] args) {
        var solution = new ConstructBinaryTreeFromInorderAndPostorder();
        TreeNode root = solution.buildTree(new int[]{2, 3, 1}, new int[]{3, 2, 1});
        System.out.println(root);
    }

}
