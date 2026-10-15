package org.lyndon.algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * LC658
 */
public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - k;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (x - arr[mid] > arr[mid + k] - x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = lo; i < lo + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            if (r - l + 1 == k) {
                break;
            }
            if (x - arr[l] <= arr[r] - x) {
                r--;
            } else {
                l++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            result.add(arr[i]);
        }
        return result;
    }

}
