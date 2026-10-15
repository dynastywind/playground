package org.lyndon.algo.array;

import java.util.Random;

/**
 * LC462
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * <p>
 * In one move, you can increment or decrement an element of the array by 1.
 * <p>
 * Test cases are designed so that the answer will fit in a 32-bit integer.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * Example 2:
 * <p>
 * Input: nums = [1,10,2,9]
 * Output: 16
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class MinMoveEqualArrayElementsII {

    public int minMoves2(int[] nums) {
        Random random = new Random();
        int low = 0;
        int high = nums.length - 1;
        int target = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        int ans = 0;
        while (true) {
            int[] pos = partition(nums, low, high, random);
            if (target < pos[0]) {
                high = pos[0] - 1;
            } else if (target > pos[1]) {
                low = pos[1] + 1;
            } else {
                for (int x : nums) {
                    ans += Math.abs(x - nums[target]);
                }
                return ans;
            }
        }
    }

    private int[] partition(int[] nums, int low, int high, Random random) {
        int lt = low;
        int gt = high;
        int i = low;
        int pivot = nums[low + random.nextInt(high - low + 1)];
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, i++, lt++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        var solution = new MinMoveEqualArrayElementsII();
        System.out.println(solution.minMoves2(new int[]{1, 2, 3}));
    }

}
