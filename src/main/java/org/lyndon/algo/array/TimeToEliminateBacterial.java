package org.lyndon.algo.array;

import java.util.PriorityQueue;

/**
 * LC3506
 */
public class TimeToEliminateBacterial {

    public long minEliminationTime(int[] timeReq, int splitTime) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int x : timeReq) {
            pq.offer((long) x);
        }

        while (pq.size() > 1) {
            pq.poll();
            pq.offer(splitTime + pq.poll());
        }
        return pq.poll();
    }

}
