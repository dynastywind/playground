package org.lyndon.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * LC2502
 */
public class Allocator {

    private final TreeMap<Integer, Integer> free = new TreeMap<>();
    private final Map<Integer, List<int[]>> owner = new TreeMap<>();

    public Allocator(int n) {
        free.put(0, n);
    }

    public int allocate(int size, int mID) {
        int s = -1;
        int e = -1;
        for (Map.Entry<Integer, Integer> entry : free.entrySet()) {
            if (entry.getValue() - entry.getKey() >= size) {
                s = entry.getKey();
                e = entry.getValue();
                break;
            }
        }
        if (s == -1) {
            return -1;
        }

        free.remove(s);
        if (s + size < e) {
            free.put(s + size, e);
        }
        owner.computeIfAbsent(mID, k -> new ArrayList<>()).add(new int[]{s, s + size});
        return s;
    }

    public int freeMemory(int mID) {
        List<int[]> removed = owner.remove(mID);
        if (removed == null) {
            return 0;
        }

        int freed = 0;
        for (int[] block : removed) {
            int s = block[0];
            int e = block[1];
            freed += e - s;

            Map.Entry<Integer, Integer> left = free.floorEntry(s);
            if (left != null && left.getValue() == s) {
                s = left.getKey();
                free.remove(left.getKey());
            }
            Integer rightEnd = free.get(e);
            if (rightEnd != null) {
                free.remove(e);
                e = rightEnd;
            }
            free.put(s, e);
        }
        return freed;
    }

}
