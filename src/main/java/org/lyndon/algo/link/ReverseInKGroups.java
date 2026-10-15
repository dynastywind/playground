package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

/**
 * LC25
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 */
public class ReverseInKGroups {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = null;
        ListNode cur = head;
        ListNode groupBefore = dummy;
        while (cur != null) {
            ListNode kth = cur;
            int cnt = 0;
            for (; cnt < k && kth != null; cnt++) {
                kth = kth.next;
            }
            if (kth == null && cnt != k) {
                break;
            }

            ListNode groupStart = cur;
            for (int i = 0; i < k; i++) {
                if (cur == null) {
                    break;
                }
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }
            groupBefore.next = prev;
            groupStart.next = cur;
            groupBefore = groupStart;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        var solution = new ReverseInKGroups();
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode head = new ListNode(1, new ListNode(2, null));
        System.out.println(solution.reverseKGroup(head, 2).print());
    }

}
