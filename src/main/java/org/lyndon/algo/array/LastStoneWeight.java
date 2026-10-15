package org.lyndon.algo.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LC1046
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            pq.offer(stone);
        }
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            pq.offer(Math.abs(a - b));
        }
        return pq.peek();
    }

}
