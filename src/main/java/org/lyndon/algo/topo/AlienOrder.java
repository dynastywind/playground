package org.lyndon.algo.topo;

import java.util.*;

public class AlienOrder {

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        // init
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        // create edges
        for (int i = 0; i < words.length - 1; i++) {
            int length = Math.min(words[i].length(), words[i + 1].length());
            boolean found = false;
            for (int j = 0; j < length; j++) {
                char c1 = words[i].charAt(j);
                char c2 = words[i + 1].charAt(j);
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    found = true;
                    break;
                }
            }
            // invalid order. e.g. ["abc", "ab"]
            if (!found && words[i].length() > words[i + 1].length()) {
                return "";
            }
        }

        // topological sort
        Queue<Character> queue = new LinkedList<>();
        inDegree.forEach((k, v) -> {
            if (v == 0) {
                queue.add(k);
            }
        });
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            for (char child : graph.get(c)) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    queue.add(child);
                }
            }
            sb.append(c);
        }
        return sb.length() == graph.size() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        var order = new AlienOrder();
        // wertf
        System.out.println(order.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        // xyz
        System.out.println(order.alienOrder(new String[]{"x", "y", "z"}));
        // ""
        System.out.println(order.alienOrder(new String[]{"x", "y", "x"}));
    }

}
