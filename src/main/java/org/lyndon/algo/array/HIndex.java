package org.lyndon.algo.array;

/**
 * LC274
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that the given researcher has published at least h papers that have each been cited at least h times.
 */
public class HIndex {

    public int hIndex(int[] citations) {
        int lo = 0;
        int hi = citations.length;
        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (check(citations, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
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
