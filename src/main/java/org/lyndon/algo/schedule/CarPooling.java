package org.lyndon.algo.schedule;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CarPooling {

    private static class Desc {
        int time;
        boolean in;
        int passengers;

        public Desc(int time, boolean in, int passengers) {
            this.time = time;
            this.in = in;
            this.passengers = passengers;
        }
    }

    public boolean carPooling(int[][] trips, int capacity) {
        List<Desc> list = new LinkedList<>();
        for (int[] trip : trips) {
            list.add(new Desc(trip[1], true, trip[0]));
            list.add(new Desc(trip[2], false, trip[0]));
        }
        list.sort((o1, o2) -> {
            int diff = o1.time - o2.time;
            if (diff != 0) {
                return diff;
            }
            return o1.in ? 1 : -1;
        });
        int cur = 0;
        for (Desc desc : list) {
            if (desc.in) {
                cur += desc.passengers;
            } else {
                cur = Math.max(0, cur - desc.passengers);
            }
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }

    public boolean carPoolingTimestamp(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] trip : trips) {
            int up = map.getOrDefault(trip[1], 0) + trip[0];
            map.put(trip[1], up);

            int down = map.getOrDefault(trip[2], 0) - trip[0];
            map.put(trip[2], down);
        }
        int used = 0;
        for (int passengers : map.values()) {
            used += passengers;
            if (used > capacity) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        var solution = new CarPooling();
        System.out.println(solution.carPooling(new int[][]{new int[]{2, 1, 5}, new int[]{3, 3, 7}}, 4));
        System.out.println(solution.carPooling(new int[][]{new int[]{2, 1, 5}, new int[]{3, 3, 7}}, 5));
        System.out.println(solution.carPooling(new int[][]{new int[]{4, 5, 6}, new int[]{6, 4, 7}, new int[]{4, 3, 5}, new int[]{2, 3, 5}}, 13));
    }

}
