package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC1029
 */
public class TwoCityScheduling {

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
        int n = costs.length / 2;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += costs[i][0];
            ans += costs[i + n][1];
        }
        return ans;
    }

}
