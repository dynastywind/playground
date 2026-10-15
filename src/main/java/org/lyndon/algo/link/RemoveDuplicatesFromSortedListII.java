package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

/**
 * LC82
 */
public class RemoveDuplicatesFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        boolean isDuplicate = false;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (isDuplicate) {
                prev.next = cur.next;
            }
            if (cur.next == null) {
                break;
            }
            if (isDuplicate) {
                if (cur.next.val != cur.val) {
                    isDuplicate = false;
                }
            } else {
                if (cur.next.val == cur.val) {
                    isDuplicate = true;
                } else {
                    prev = cur;
                }
            }
            cur = cur.next;
        }
        return dummy.next;
    }

}
