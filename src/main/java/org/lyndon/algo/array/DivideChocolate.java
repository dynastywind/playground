package org.lyndon.algo.array;

/**
 * LC1231
 */
public class DivideChocolate {

    public int maximizeSweetness(int[] sweetness, int k) {
        int lo = Integer.MAX_VALUE;
        int hi = 0;
        for (int sweet : sweetness) {
            lo = Math.min(lo, sweet);
            hi += sweet;
        }
        hi /= k + 1;
        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(sweetness, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] sweetness, int k, int target) {
        int sum = 0;
        int groups = 0;
        for (int sweet : sweetness) {
            sum += sweet;
            if (sum >= target) {
                groups++;
                sum = 0;
            }
            if (groups >= k + 1) {
                return true;
            }
        }
        return groups >= k + 1;
    }

}
