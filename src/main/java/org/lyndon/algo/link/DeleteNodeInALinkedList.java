package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

/**
 * LC237
 */
public class DeleteNodeInALinkedList {

    public void deleteNode(ListNode node) {
        ListNode cur = node;
        while (cur.next.next != null) {
            cur.val = cur.next.val;
            cur = cur.next;
        }
        cur.val = cur.next.val;
        cur.next = null;
    }

    public static void main(String[] args) {
        var solution = new DeleteNodeInALinkedList();
        ListNode head = ListNode.from("1,2,3,4,5,6,7");
        solution.deleteNode(head.next.next.next.next.next);
        System.out.println(head.print());
    }

}
