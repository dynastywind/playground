package org.lyndon.algo.array;

public class MedianInKSortedArrays {

    public double median(int[][] arrays) {
        int n = 0;
        long lo = Long.MAX_VALUE;
        long hi = Long.MIN_VALUE;
        for (int[] arr : arrays) {
            if (arr.length == 0) {
                continue;
            }
            n += arr.length;
            lo = Math.min(lo, arr[0]);
            hi = Math.max(hi, arr[arr.length - 1]);
        }
        long r = kth(arrays, n / 2, lo, hi);
        if ((n & 1) == 1) {
            return r;
        }
        long l = kth(arrays, n / 2 - 1, lo, r);
        return (l + r) / 2.0;
    }

    private int upperBound(int[] arr, long x) {
        int lo = 0;
        int hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (arr[mid] <= x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private long kth(int[][] arrays, long k, long lo, long hi) {
        while (lo < hi) {
            long mid = (lo + hi) >> 1;
            long cnt = 0;
            for (int[] arr : arrays) {
                if (arr.length == 0) {
                    continue;
                }
                cnt += upperBound(arr, mid);
            }
            if (cnt >= k + 1) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

}
