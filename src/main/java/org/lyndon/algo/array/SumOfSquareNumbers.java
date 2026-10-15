package org.lyndon.algo.array;

/**
 * LC633
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 * <p>
 * Example 1:
 * <p>
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 * <p>
 * Input: c = 3
 * Output: false
 */
public class SumOfSquareNumbers {

    public boolean judgeSquareSum(int c) {
        long l = 0;
        long r = (long) Math.sqrt(c);
        while (l <= r) {
            long x = l * l;
            long y = r * r;
            long sum = x + y;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var solution = new SumOfSquareNumbers();
        System.out.println(solution.judgeSquareSum(2147483600));
    }

}
