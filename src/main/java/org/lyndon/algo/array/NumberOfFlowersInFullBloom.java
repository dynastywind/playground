package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC2251
 * You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.
 * <p>
 * Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.
 */
public class NumberOfFlowersInFullBloom {

    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int[] starts = new int[flowers.length];
        int[] ends = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            starts[i] = flowers[i][0];
            ends[i] = flowers[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int[] ans = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            ans[i] = find(starts, people[i]) - find(ends, people[i] - 1);
        }
        return ans;
    }

    private int find(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
