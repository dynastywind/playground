package org.lyndon.algo.array;

/**
 * LC457
 * You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of indices forward/backward you must move if you are located at index i:
 * <p>
 * If nums[i] is positive, move nums[i] steps forward, and
 * If nums[i] is negative, move abs(nums[i]) steps backward.
 * Since the array is circular, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.
 * <p>
 * A cycle in the array consists of a sequence of indices seq of length k where:
 * <p>
 * Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * Every nums[seq[j]] is either all positive or all negative.
 * k > 1
 * Return true if there is a cycle in nums, or false otherwise
 */
public class CircularArrayLoop {

    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int slow = i, fast = next(nums, i);
            while (true) {
                if (nums[slow] * nums[next(nums, slow)] < 0 || nums[fast] * nums[next(nums, fast)] < 0) {
                    break;
                }
                if (slow == fast) {
                    if (next(nums, slow) != slow) {
                        return true;
                    }
                    break;
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
        }
        return false;
    }

    private int next(int[] nums, int from) {
        int n = nums.length;
        int move = from + nums[from];
        if (move >= 0) {
            return move % n;
        }
        return (move % n + n) % n;
    }

    public static void main(String[] args) {
        var solution = new CircularArrayLoop();
        var a = new int[]{2, -1, 1, 2, 2};
//        System.out.println(solution.circularArrayLoop(a));
//        System.out.println(solution.circularArrayLoop(new int[]{-1, -2, -3, -4, -5, 6}));
//        System.out.println(solution.circularArrayLoop(new int[]{1, -1, 5, 1, 4}));
        System.out.println(solution.circularArrayLoop(new int[]{1, -1}));
    }

}
