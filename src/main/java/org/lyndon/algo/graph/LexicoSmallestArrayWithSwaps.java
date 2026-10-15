package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * LC2948
 */
public class LexicoSmallestArrayWithSwaps {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        List<Integer> pos = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            pos.add(i);
        }
        pos.sort(Comparator.comparingInt(a -> nums[a]));

        int i = 0;
        int[] res = new int[n];
        while (i < n) {
            int j = i;
            while (j + 1 < n && nums[pos.get(j + 1)] - nums[pos.get(j)] <= limit) {
                j++;
            }
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k <= j; k++) {
                indices.add(pos.get(k));
            }
            Collections.sort(indices);
            for (int k = 0; k < indices.size(); k++) {
                res[indices.get(k)] = nums[pos.get(i + k)];
            }
            i = j + 1;
        }
        return res;
    }

}
