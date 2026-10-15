package org.lyndon.algo.sort;

import org.lyndon.algo.common.ListNode;

/**
 * LC148
 * Given the head of a linked list, return the list after sorting it in ascending order.
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = (left == null) ? right : left;
        return dummy.next;
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            if (prev == null) {
                prev = slow;
            } else {
                prev = prev.next;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        return slow;
    }

}
