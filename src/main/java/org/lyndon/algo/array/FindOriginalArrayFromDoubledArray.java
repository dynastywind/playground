package org.lyndon.algo.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LC2007
 */
public class FindOriginalArrayFromDoubledArray {

    // 1 2 2 2 4 4
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n == 0 || n % 2 == 1) {
            return new int[0];
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : changed) {
            cnt.merge(num, 1, Integer::sum);
        }
        Arrays.sort(changed);
        int[] res = new int[n / 2];
        int i = 0;
        for (int num : changed) {
            int baseCnt = cnt.get(num);
            if (baseCnt <= 0) {
                continue;
            }
            int doubleCnt = cnt.getOrDefault(num * 2, 0);
            if (doubleCnt < baseCnt) {
                return new int[0];
            }
            cnt.merge(num, -1, Integer::sum);
            cnt.merge(num * 2, -1, Integer::sum);
            res[i++] = num;
        }
        return res;
    }

}
