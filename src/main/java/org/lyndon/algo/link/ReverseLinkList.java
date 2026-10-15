package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

public class ReverseLinkList {

    private static ListNode reverse(ListNode head) {
        ListNode current = head, prev = null;
        while (current != null) {
            ListNode tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        return prev;
    }

    public static void main(String[] args) {
        System.out.println(reverse(ListNode.from("1->2->3->4->5")).print());
    }

}
