package org.lyndon.design;

import java.util.PriorityQueue;

/**
 * LC1845
 */
public class SeatManager {

    private final PriorityQueue<Integer> returned;

    private int max;

    public SeatManager(int n) {
        this.returned = new PriorityQueue<>(n);
        this.max = 0;
    }

    public int reserve() {
        if (!returned.isEmpty()) {
            return returned.poll();
        }
        return ++max;
    }

    public void unreserve(int seatNumber) {
        if (seatNumber > max) {
            return;
        }
        if (seatNumber == max) {
            max--;
            return;
        }
        returned.add(seatNumber);
    }

}
