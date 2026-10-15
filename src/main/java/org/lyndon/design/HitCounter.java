package org.lyndon.design;

public class HitCounter {

    private static final int SIZE = 300;

    private final int[] ring;

    private final int[] count;

    public HitCounter() {
        this.ring = new int[SIZE];
        this.count = new int[SIZE];
    }

    public void hit(int timestamp) {
        int index = timestamp % SIZE;
        if (ring[index] != timestamp) {
            ring[index] = timestamp;
            count[index] = 1;
        } else {
            count[index]++;
        }
    }

    public int getHits(int timestamp) {
        int hits = 0;
        for (int i = 0; i < SIZE; i++) {
            if (timestamp - ring[i] < SIZE) {
                hits += count[i];
            }
        }
        return hits;
    }

}
