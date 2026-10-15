package org.lyndon.algo.array;

import java.util.Map;
import java.util.TreeMap;

/**
 * LC1094
 */
public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] trip : trips) {
            map.merge(trip[1], trip[0], Integer::sum);
            map.merge(trip[2], -trip[0], Integer::sum);
        }
        int used = 0;
        for (int v : map.values()) {
            used += v;
            if (used > capacity) {
                return false;
            }
        }
        return true;
    }

}
