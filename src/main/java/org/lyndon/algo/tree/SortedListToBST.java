package org.lyndon.algo.tree;

import org.lyndon.algo.common.ListNode;

/**
 * LC109
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 * DSW algo.
 */
public class SortedListToBST {

    public TreeNode sortedListToBST(ListNode head) {
        ListNode list = head;
        TreeNode root = new TreeNode();
        TreeNode node = root;
        int size = 0;
        while (list != null) {
            node.right = new TreeNode(list.val);
            node = node.right;
            list = list.next;
            size++;
        }
        // 2^k = highestBit(n + 1)
        // 2^k -1 <= n -> k <= log2(n + 1)
        int leaves = size + 1 - Integer.highestOneBit(size + 1);
        compress(root, leaves);
        size -= leaves;
        while (size > 1) {
            size /= 2;
            compress(root, size);
        }
        return root.right;
    }

    // r-1     -2-3-4
    //   child cur
    private void compress(TreeNode root, int count) {
        TreeNode cur = root;
        for (int i = 0; i < count; i++) {
            TreeNode child = cur.right;
            cur.right = child.right;
            cur = cur.right;
            child.right = cur.left;
            cur.left = child;
        }
    }

}
