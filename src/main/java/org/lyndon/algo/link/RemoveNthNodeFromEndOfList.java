package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

/**
 * LC19
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        if (prev == null) {
            head = slow.next;
        } else {
            prev.next = slow.next;
        }
        slow.next = null;
        return head;
    }

}
