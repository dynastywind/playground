package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

public class ReverseLinkII {

    private static ListNode reverse(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        int i = 1;
        while (i < left) {
            prev = cur;
            cur = cur.next;
            i++;
        }
        ListNode before = prev;
        ListNode start = cur;
        while (i <= right) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            i++;
        }
        start.next = cur;
        before.next = prev;
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(reverse(ListNode.from("1->2->3->4->5"), 1, 5).print());
    }

}
