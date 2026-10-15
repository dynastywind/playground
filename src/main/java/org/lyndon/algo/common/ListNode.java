package org.lyndon.algo.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ListNode {
    public Integer val;
    public ListNode next;

    public static ListNode from(String s) {
        String[] values;
        if (s.contains("->")) {
            values = s.split("->");
        } else {
            values = s.split(",");
        }
        ListNode head = new ListNode(null, null), next = head;
        for (String value : values) {
            next.next = new ListNode(Integer.parseInt(value), null);
            next = next.next;
        }
        return head.next;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }
}
