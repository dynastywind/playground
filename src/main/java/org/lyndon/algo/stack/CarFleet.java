package org.lyndon.algo.stack;

import java.util.Arrays;

/**
 * LC853
 */
public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0;
        double lead = 0.0;
        for (int[] car : cars) {
            double t = (double) (target - car[0]) / car[1];
            if (t > lead) {
                ans++;
                lead = t;
            }
        }
        return ans;
    }

}
