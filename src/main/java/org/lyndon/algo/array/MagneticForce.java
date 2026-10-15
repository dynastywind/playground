package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC1552
 */
public class MagneticForce {

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int lo = 1;
        int hi = (position[n - 1] - position[0]) / (m - 1);
        int ans = lo;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(position, m, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] position, int m, int force) {
        int cnt = 1;
        int prev = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - prev >= force) {
                cnt++;
                prev = position[i];
                if (cnt >= m) {
                    return true;
                }
            }
        }
        return false;
    }

}
