package org.lyndon.design;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Queue;

/**
 * LC3829
 */
public class RideSharingSystem {

    private final LinkedHashSet<Integer> riders;
    private final Queue<Integer> drivers;

    public RideSharingSystem() {
        this.riders = new LinkedHashSet<>();
        this.drivers = new ArrayDeque<>();
    }

    public void addRider(int riderId) {
        riders.addLast(riderId);
    }

    public void addDriver(int driverId) {
        drivers.offer(driverId);
    }

    public int[] matchDriverWithRider() {
        if (!riders.isEmpty() && !drivers.isEmpty()) {
            return new int[]{drivers.poll(), riders.removeFirst()};
        }
        return new int[]{-1, -1};
    }

    public void cancelRider(int riderId) {
        riders.remove(riderId);
    }

}
