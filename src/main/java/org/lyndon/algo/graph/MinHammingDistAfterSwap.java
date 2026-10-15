package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC1722
 */
public class MinHammingDistAfterSwap {

    private int[] parent;
    private int[] rank;

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return;
        }
        if (rank[ra] < rank[rb]) {
            int tmp = ra;
            ra = rb;
            rb = tmp;
        }
        parent[rb] = ra;
        if (rank[ra] == rank[rb]) {
            rank[ra]++;
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] swaps : allowedSwaps) {
            union(swaps[0], swaps[1]);
        }
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(find(i), k -> new ArrayList<>()).add(i);
        }
        int ans = 0;
        for (List<Integer> group : groups.values()) {
            Map<Integer, Integer> a = new HashMap<>(group.size());
            List<Integer> b = new ArrayList<>(group.size());
            for (int i : group) {
                a.merge(source[i], 1, Integer::sum);
                b.add(target[i]);
            }
            for (int i : b) {
                if (!a.containsKey(i)) {
                    continue;
                }
                if (a.merge(i, -1, Integer::sum) == 0) {
                    a.remove(i);
                }
            }
            for (int cnt : a.values()) {
                ans += cnt;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new MinHammingDistAfterSwap();
        System.out.println(solution.minimumHammingDistance(new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 5}, new int[][]{{0, 1}, {2, 3}}));
    }

}
