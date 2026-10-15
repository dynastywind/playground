package org.lyndon.algo.dp;

/**
 * LC600
 * Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5
 * Output: 5
 * Explanation:
 * Here are the non-negative integers <= 5 with their corresponding binary representations:
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 2
 * Example 3:
 * <p>
 * Input: n = 2
 * Output: 3
 */
public class NonNegativeIntsWithoutConsecutiveOnes {

    private int[] bits = new int[31];

    private int len;

    private Integer[][] memo = new Integer[31][2];

    public int findIntegers(int n) {
        for (int i = 30; i >= 0; i--) {
            bits[len++] = (n >> i) & 1;
        }
        return dfs(0, 0, true);
    }

    private int dfs(int i, int pre, boolean tight) {
        if (i == len) {
            return 1;
        }
        if (!tight && memo[i][pre] != null) {
            return memo[i][pre];
        }

        int up = tight ? bits[i] : 1;
        int res = 0;
        for (int d = 0; d <= up; d++) {
            if (pre == 1 && d == 1) {
                continue;
            }
            res += dfs(i + 1, d, tight && d == up);
        }
        if (!tight) {
            memo[i][pre] = res;
        }
        return res;
    }

}
