package org.lyndon.algo.link;

import org.lyndon.algo.common.ListNode;

/**
 * LC2058
 */
public class MinAndMaxNodesBetweenCriticalPoints {

    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = new int[]{-1, -1};
        if (head == null || head.next == null || head.next.next == null) {
            return ans;
        }

        int first = -1;
        int prev = -1;
        int last = -1;
        int minDist = Integer.MAX_VALUE;

        int index = 1;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur.next != null) {
            boolean isLocalMax = cur.val > pre.val && cur.val > cur.next.val;
            boolean isLocalMin = cur.val < pre.val && cur.val < cur.next.val;
            if (isLocalMax || isLocalMin) {
                if (first == -1) {
                    first = index;
                } else {
                    minDist = Math.min(minDist, index - prev);
                }
                prev = index;
                last = index;
            }
            pre = cur;
            cur = cur.next;
            index++;
        }

        if (first == last) {
            return ans;
        }
        ans[0] = minDist;
        ans[1] = last - first;
        return ans;
    }

}
