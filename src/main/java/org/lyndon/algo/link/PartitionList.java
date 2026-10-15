package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

/**
 * LC86
 */
public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode();
        ListNode lessHead = lessDummy;
        ListNode greaterDummy = new ListNode();
        ListNode greaterHead = greaterDummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                lessHead.next = cur;
                lessHead = lessHead.next;
            } else {
                greaterHead.next = cur;
                greaterHead = greaterHead.next;
            }
            cur = cur.next;
        }
        greaterHead.next = null;
        lessHead.next = greaterDummy.next;
        return lessDummy.next;
    }

}
