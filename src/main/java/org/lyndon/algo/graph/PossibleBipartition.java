package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LC886
 */
public class PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }
        int[] color = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (color[i] != 0) {
                continue;
            }
            if (!solve(i, 1, color, graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean solve(int i, int c, int[] color, List<Integer>[] graph) {
        color[i] = c;
        for (int v : graph[i]) {
            if (color[v] == 0) {
                if (!solve(v, -c, color, graph)) {
                    return false;
                }
            } else if (color[v] == c) {
                return false;
            }
        }
        return true;
    }

}
