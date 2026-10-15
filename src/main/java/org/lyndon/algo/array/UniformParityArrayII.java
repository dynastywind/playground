package org.lyndon.algo.array;

import java.util.TreeSet;

/**
 * LC3876
 */
public class UniformParityArrayII {

    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums1) {
            min = Math.min(min, num);
            if (num % 2 == 1) {
                set.add(num);
            }
        }
        boolean even = min % 2 == 0;
        for (int num : nums1) {
            boolean isEven = num % 2 == 0;
            if (isEven && even || !isEven && !even) {
                continue;
            }
            Integer less = set.floor(num - 1);
            if (less == null) {
                return false;
            }
        }
        return true;
    }

    static void main() {
        var solution = new UniformParityArrayII();
        System.out.println(solution.uniformArray(new int[]{1, 4, 7}));
        System.out.println(solution.uniformArray(new int[]{2, 3}));
        System.out.println(solution.uniformArray(new int[]{4, 6}));
        System.out.println(solution.uniformArray(new int[]{10, 13}));
    }

}
