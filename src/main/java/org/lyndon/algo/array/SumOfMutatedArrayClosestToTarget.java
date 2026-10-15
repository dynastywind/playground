package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC1300
 */
public class SumOfMutatedArrayClosestToTarget {

    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            int rem = n - i;
            if (pre + arr[i] * rem >= target) {
                int x = (target - pre) / rem;
                int diff1 = target - (pre + x * rem);
                int diff2 = pre + (x + 1) * rem - target;
                return diff1 <= diff2 ? x : x + 1;
            }
            pre += arr[i];
        }
        return arr[n - 1];
    }

}
