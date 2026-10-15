package org.lyndon.algo.tree;

/**
 * LC255
 */
public class VerifyPreorderSeqInBST {

    public boolean verifyPreorder(int[] preorder) {
        return check(preorder, 0, preorder.length - 1);
    }

    private boolean check(int[] preorder, int l, int r) {
        if (l >= r) {
            return true;
        }
        int root = preorder[l];
        int i = l + 1;
        while (i <= r && preorder[i] < root) {
            i++;
        }
        int j = i;
        while (j <= r && preorder[j] > root) {
            j++;
        }
        if (j <= r) {
            return false;
        }
        return check(preorder, l + 1, i - 1) && check(preorder, i, r);
    }

    public static void main(String[] args) {
        var solution = new VerifyPreorderSeqInBST();
        System.out.println(solution.verifyPreorder(new int[]{1, 2, 3, 4, 5}));
    }

}
