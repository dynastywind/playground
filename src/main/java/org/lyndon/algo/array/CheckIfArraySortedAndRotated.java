package org.lyndon.algo.array;

/**
 * LC1752
 */
public class CheckIfArraySortedAndRotated {

    public boolean check(int[] nums) {
        int n = nums.length;
        boolean changed = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                if (changed) {
                    return false;
                }
                changed = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        var sort = new CheckIfArraySortedAndRotated();
        System.out.println(sort.check(new int[]{3, 4, 5, 1, 2}));
        System.out.println(sort.check(new int[]{2, 1, 3, 4}));
        System.out.println(sort.check(new int[]{1, 2, 3}));
    }

}
