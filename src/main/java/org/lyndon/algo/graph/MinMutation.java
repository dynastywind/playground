package org.lyndon.algo.graph;

import java.util.*;

/**
 * LC433
 */
public class MinMutation {

    public int minMutation(String startGene, String endGene, String[] bank) {
        final char[] GENES = {'A', 'C', 'G', 'T'};
        if (startGene.equals(endGene)) {
            return 0;
        }
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(endGene)) {
            return -1;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] cur = queue.poll().toCharArray();
                for (int j = 0; j < cur.length; j++) {
                    char origin = cur[j];
                    for (char g : GENES) {
                        if (g == origin) {
                            continue;
                        }
                        cur[j] = g;
                        String next = new String(cur);
                        if (set.contains(next) && visited.add(next)) {
                            if (next.equals(endGene)) {
                                return steps;
                            }
                            queue.offer(next);
                        }
                    }
                    cur[j] = origin;
                }
            }
        }
        return -1;
    }

}
