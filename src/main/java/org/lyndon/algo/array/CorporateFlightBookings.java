package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

/**
 * LC1109
 */
public class CorporateFlightBookings {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 2];
        for (int[] booking : bookings) {
            diff[booking[0]] += booking[2];
            diff[booking[1] + 1] -= booking[2];
        }
        int[] ans = new int[n];
        for (int i = 1; i <= n; i++) {
            ans[i - 1] = (i == 1 ? 0 : ans[i - 2]) + diff[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new CorporateFlightBookings();
        PrintUtils.printArray(solution.corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5));
        PrintUtils.printArray(solution.corpFlightBookings(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2));
    }

}
