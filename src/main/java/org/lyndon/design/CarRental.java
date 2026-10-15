package org.lyndon.design;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class CarRental {

    /**
     * assign
     *
     * @param req {{id, pickup, return}}
     */
    public int[] assign(int[][] req) {
        int n = req.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> {
            if (req[a][1] != req[b][1]) {
                return Integer.compare(req[a][1], req[b][1]);
            }
            return Integer.compare(req[a][2], req[b][2]);
        });
        // {next_available, carId}
        PriorityQueue<int[]> used = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        TreeSet<Integer> free = new TreeSet<>();
        int carId = 0;
        int[] ans = new int[n];
        for (int i : order) {
            while (!used.isEmpty() && used.peek()[0] <= req[i][1]) {
                free.add(used.poll()[1]);
            }
            if (!free.isEmpty()) {
                ans[i] = free.removeFirst();
            } else {
                ans[i] = carId++;
            }
            used.offer(new int[]{req[i][2], ans[i]});
        }
        return ans;
    }

    public int maxServed(int[][] req, int N) {
        int[][] requests = req.clone();
        Arrays.sort(requests, (a, b) -> Integer.compare(a[2], b[2]));
        TreeMap<Integer, Integer> freeAt = new TreeMap<>();
        freeAt.put(Integer.MIN_VALUE, N);
        int served = 0;
        for (int[] r : requests) {
            Integer k = freeAt.floorKey(r[1]);
            if (k == null) {
                continue;
            }
            if (freeAt.merge(k, -1, Integer::sum) == 0) {
                freeAt.remove(k);
            }
            freeAt.merge(r[2], 1, Integer::sum);
            served++;
        }
        return served;
    }

}
