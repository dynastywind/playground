package org.lyndon.algo.array;

/**
 * LC1558
 */
public class MinCallsToMakeTargetArray {

    public int minOperations(int[] nums) {
        int adds = 0;
        int maxHighBit = 0;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            adds += Integer.bitCount(num);
            maxHighBit = Math.max(maxHighBit, 31 - Integer.numberOfLeadingZeros(num));
        }
        return adds + maxHighBit;
    }

    public int minOperations2(int[] nums) {
        int n = nums.length;
        int ops = 0;
        while (true) {
            boolean allZero = true;
            for (int i = 0; i < n; i++) {
                if ((nums[i] & 1) == 1) {
                    nums[i]--;
                    ops++;
                }
                if (nums[i] != 0) {
                    allZero = false;
                }
            }
            if (allZero) {
                break;
            }
            for (int i = 0; i < n; i++) {
                nums[i] >>= 1;
            }
            ops++;
        }
        return ops;
    }

    static void main() {
        var solution = new MinCallsToMakeTargetArray();
        System.out.println(solution.minOperations(new int[]{2, 4, 8, 16}));
        System.out.println(solution.minOperations(new int[]{2, 4, 8, 6}));
        System.out.println(solution.minOperations(new int[]{2, 4, 5}));
        System.out.println(solution.minOperations(new int[]{1, 5}));
        System.out.println(solution.minOperations(new int[]{1000000000}));
    }

}
