package org.lyndon.algo.meeting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC1353
 */
public class MaxEventsToAttend {

    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        int n = events.length;
        int day = 0;
        int ans = 0;
        while (i < n || !pq.isEmpty()) {
            if (pq.isEmpty()) {
                day = events[i][0];
            }
            while (i < n && events[i][0] <= day) {
                pq.offer(events[i++][1]);
            }
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
                day++;
            }
        }
        return ans;
    }

}
