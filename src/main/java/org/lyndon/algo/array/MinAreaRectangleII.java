package org.lyndon.algo.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC963
 */
public class MinAreaRectangleII {

    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        Map<String, List<int[]>> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long sx = (long) points[i][0] + points[j][0];
                long sy = (long) points[i][1] + points[j][1];
                long d = dist(points[i], points[j]);
                String key = sx + "," + sy + "," + d;
                groups.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        double ans = Double.MAX_VALUE;
        for (List<int[]> group : groups.values()) {
            int k = group.size();
            for (int a = 0; a < k; a++) {
                int[] p = points[group.get(a)[0]];
                for (int b = a + 1; b < k; b++) {
                    int[] q = points[group.get(b)[0]];
                    int[] r = points[group.get(b)[1]];
                    double area = Math.sqrt(dist(p, q)) * Math.sqrt(dist(p, r));
                    ans = Math.min(ans, area);
                }
            }
        }
        return ans == Double.MAX_VALUE ? 0 : ans;
    }

    private long dist(int[] a, int[] b) {
        long dx = a[0] - b[0];
        long dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }

}
