package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC473
 */
public class MakeSquare {

    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;
        long total = 0;
        for (int m : matchsticks) {
            total += m;
        }
        if (total % 4 != 0) {
            return false;
        }
        int limit = (int) (total / 4);
        Arrays.sort(matchsticks);
        if (matchsticks[n - 1] > limit) {
            return false;
        }
        return dfs(matchsticks, n - 1, new int[4], limit);
    }

    private boolean dfs(int[] matchsticks, int i, int[] edges, int limit) {
        if (i == -1) {
            return true;
        }
        for (int j = 0; j < edges.length; j++) {
            if (edges[j] + matchsticks[i] > limit) {
                continue;
            }
            edges[j] += matchsticks[i];
            if (dfs(matchsticks, i - 1, edges, limit)) {
                return true;
            }
            edges[j] -= matchsticks[i];
        }
        return false;
    }

}
