package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * LC835
 */
public class ImageOverlap {

    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    ones1.add(new int[]{i, j});
                }
                if (img2[i][j] == 1) {
                    ones2.add(new int[]{i, j});
                }
            }
        }

        int size = 2 * n - 1;
        int[] cnt = new int[size * size];
        int ans = 0;
        for (int[] a : ones1) {
            for (int[] b : ones2) {
                int dx = b[0] - a[0] + n - 1;
                int dy = b[1] - a[1] + n - 1;
                int key = dx * size + dy;
                ans = Math.max(ans, ++cnt[key]);
            }
        }
        return ans;
    }

}
