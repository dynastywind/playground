package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

/**
 * LC61
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        k %= len;
        if (k == 0) {
            return head;
        }

        ListNode prev = null;
        cur = head;
        int i = 0;
        while (i < len - k) {
            prev = cur;
            cur = cur.next;
            i++;
        }
        prev.next = null;
        ListNode newHead = cur;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        var solution = new RotateList();
        System.out.println(solution.rotateRight(ListNode.from("1"), 2).print());
        System.out.println(solution.rotateRight(ListNode.from("1->2"), 1).print());
        System.out.println(solution.rotateRight(ListNode.from("1->2->3->4->5"), 2).print());
        System.out.println(solution.rotateRight(ListNode.from("0->1->2->3"), 4).print());
    }

}
