package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC1202
 */
public class SmallestStringWithSwaps {

    private int[] parent;
    private int[] rank;

    private int find(int x) {
        if (x != parent[x]) {
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

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }

        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        char[] result = new char[n];
        for (List<Integer> group : groups.values()) {
            List<Character> chars = new ArrayList<>();
            for (int i : group) {
                chars.add(s.charAt(i));
            }
            Collections.sort(chars);
            for (int i = 0; i < group.size(); i++) {
                result[group.get(i)] = chars.get(i);
            }
        }
        return new String(result);
    }

}
