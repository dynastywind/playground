package org.lyndon.algo.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * LC1386
 */
public class CinemaSeatAllocation {

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> seats = new HashMap<>();
        for (int[] reservedSeat : reservedSeats) {
            int row = reservedSeat[0] - 1;
            int seat = reservedSeat[1] - 1;
            seats.merge(row, 1 << seat, (a, b) -> a | b);
        }
        int ans = (n - seats.size()) * 2;
        int mask = 0b1111;
        for (int seat : seats.values()) {
            seat >>= 1;
            if ((seat & mask) == 0) {
                ans++;
                if ((seat >> 4 & mask) == 0) {
                    ans++;
                }
            } else {
                seat >>= 2;
                if ((seat & mask) == 0) {
                    ans++;
                } else {
                    seat >>= 2;
                    if ((seat & mask) == 0) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new CinemaSeatAllocation();
        System.out.println(solution.maxNumberOfFamilies(3, new int[][]{{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}}));
    }

}
