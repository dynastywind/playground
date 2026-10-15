package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC149
 */
public class MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;
                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }
                String key = dy + "/" + dx;
                ans = Math.max(ans, map.merge(key, 1, Integer::sum) + 1);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
