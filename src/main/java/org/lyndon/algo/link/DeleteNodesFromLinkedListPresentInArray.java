package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LC3217
 */
public class DeleteNodesFromLinkedListPresentInArray {

    public ListNode modifiedList(int[] nums, ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val)) {
                prev.next = cur.next;
                ListNode tmp = cur;
                cur = cur.next;
                tmp.next = null;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        var solution = new DeleteNodesFromLinkedListPresentInArray();
        System.out.println(solution.modifiedList(new int[]{1, 2, 3}, ListNode.from("1,2,3,4,5,6,7")).print());
        System.out.println(solution.modifiedList(new int[]{1}, ListNode.from("1,2,1,2,1,2")).print());
        System.out.println(solution.modifiedList(new int[]{5}, ListNode.from("1,2,3,4")).print());
    }

}
