package org.lyndon.algo.array;

/**
 * LC1891
 */
public class CutRibbons {

    public int maxLength(int[] ribbons, int k) {
        int lo = 1;
        int hi = Integer.MIN_VALUE;
        for (int r : ribbons) {
            hi = Math.max(hi, r);
        }
        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(ribbons, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] ribbons, int k, int target) {
        int cuts = 0;
        for (int r : ribbons) {
            if (r < target) {
                continue;
            }
            cuts += r / target;
        }
        return cuts >= k;
    }

    static void main() {
        var solution = new CutRibbons();
        System.out.println(solution.maxLength(new int[]{9, 7, 5}, 3));
        System.out.println(solution.maxLength(new int[]{7, 5, 9}, 4));
        System.out.println(solution.maxLength(new int[]{5, 7, 9}, 22));
        System.out.println(solution.maxLength(new int[]{100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 1, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000}, 49));
    }

}
