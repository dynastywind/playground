package org.lyndon.algo.array;

/**
 * LC2226
 */
public class MaxCandiesAllocatedToKChildren {

    public int maximumCandies(int[] candies, long k) {
        int lo = 0;
        int hi = Integer.MIN_VALUE;
        long sum = 0;
        for (int candy : candies) {
            hi = Math.max(hi, candy);
            sum += candy;
        }
        if (sum < k) {
            return 0;
        }
        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(candies, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] candies, long k, int num) {
        long valid = 0;
        for (int candy : candies) {
            valid += candy / num;
            if (valid >= k) {
                return true;
            }
        }
        return valid == k;
    }

    public static void main(String[] args) {
        var solution = new MaxCandiesAllocatedToKChildren();
        System.out.println(solution.maximumCandies(new int[]{5, 8, 6}, 3));
        System.out.println(solution.maximumCandies(new int[]{2, 5}, 11));
        System.out.println(solution.maximumCandies(new int[]{1, 2, 3, 4, 10}, 5));
    }

}
