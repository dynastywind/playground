package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

/**
 * LC1721
 */
public class SwapNodesInLinkedList {

    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prevA = dummy;
        ListNode targetA = null;
        ListNode prevB = dummy;
        ListNode targetB = null;
        ListNode slow = head;
        ListNode fast = head;
        int steps = 0;
        while (steps < k - 1) {
            steps++;
            prevA = fast;
            fast = fast.next;
        }
        targetA = fast;
        while (fast.next != null) {
            prevB = slow;
            slow = slow.next;
            fast = fast.next;
        }
        targetB = slow;

        prevA.next = targetB;
        prevB.next = targetA;
        ListNode tmp = targetA.next;
        targetA.next = targetB.next;
        targetB.next = tmp;
        return dummy.next;
    }

    public static void main(String[] args) {
        var solution = new SwapNodesInLinkedList();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(solution.swapNodes(head, 2).print());
        System.out.println(solution.swapNodes(ListNode.from("7,9,6,6,7,8,3,0,9,5"), 5).print());
        System.out.println(solution.swapNodes(ListNode.from("1,2,3,4,5"), 3).print());
        System.out.println(solution.swapNodes(ListNode.from("1"), 1).print());
        System.out.println(solution.swapNodes(ListNode.from("1,2,3,4"), 4).print());
    }

}
