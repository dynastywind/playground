package org.lyndon.algo.array;

import java.util.TreeMap;

/**
 * LC1296
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {

    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return false;
        }

        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        for (int num : nums) {
            cnt.merge(num, 1, Integer::sum);
        }
        for (int i = 0; i < n / k; i++) {
            if (cnt.isEmpty()) {
                return false;
            }
            int start = cnt.firstKey();
            if (cnt.merge(start, -1, Integer::sum) == 0) {
                cnt.remove(start);
            }
            for (int j = 1; j < k; j++) {
                int rem = cnt.merge(start + j, -1, Integer::sum);
                if (rem == -1) {
                    return false;
                } else if (rem == 0) {
                    cnt.remove(start + j);
                }
            }
        }
        return cnt.isEmpty();
    }

    static void main() {
        var solution = new DivideArrayInSetsOfKConsecutiveNumbers();
        System.out.println(solution.isPossibleDivide(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4));
        System.out.println(solution.isPossibleDivide(new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3));
        System.out.println(solution.isPossibleDivide(new int[]{1, 2, 3, 4}, 3));
    }

}
