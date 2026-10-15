package org.lyndon.algo.sort;

public class RadixSort {

    public void radixSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        }

        int min = arr[0];
        int max = arr[0];
        for (int a : arr) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = (long) arr[i] - min;
        }
        long range = (long) max - min;

        for (long exp = 1; range / exp > 0; exp *= 10) {
            long[] buf = new long[n];
            int[] cnt = new int[11];
            for (long v : a) {
                cnt[(int) (v / exp % 10) + 1]++;
            }
            for (int d = 1; d <= 10; d++) {
                cnt[d] += cnt[d - 1];
            }
            for (long v : a) {
                buf[cnt[(int) (v / exp % 10)]++] = v;
            }
            a = buf;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = (int) (a[i] + min);
        }
    }

}
