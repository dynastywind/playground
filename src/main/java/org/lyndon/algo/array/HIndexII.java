package org.lyndon.algo.array;

/**
 * LC275
 */
public class HIndexII {

    public int hIndex(int[] citations) {
        int lo = 0;
        int hi = citations.length;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (check(citations, mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private boolean check(int[] citations, int h) {
        int total = 0;
        for (int citation : citations) {
            if (citation >= h) {
                total++;
            }
        }
        return total >= h;
    }

}
