package org.lyndon.algo.array;

/**
 * LC3499
 * You are given a binary string s of length n, where:
 * <p>
 * '1' represents an active section.
 * '0' represents an inactive section.
 * You can perform at most one trade to maximize the number of active sections in s. In a trade, you:
 * <p>
 * Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
 * Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
 * Return the maximum number of active sections in s after making the optimal trade.
 * <p>
 * Note: Treat s as if it is augmented with a '1' at both ends, forming t = '1' + s + '1'. The augmented '1's do not contribute to the final count.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "01"
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "0100"
 * <p>
 * Output: 4
 * <p>
 * Explanation:
 * <p>
 * String "0100" → Augmented to "101001".
 * Choose "0100", convert "101001" → "100001" → "111111".
 * The final string without augmentation is "1111". The maximum number of active sections is 4.
 * Example 3:
 * <p>
 * Input: s = "1000100"
 * <p>
 * Output: 7
 * <p>
 * Explanation:
 * <p>
 * String "1000100" → Augmented to "110001001".
 * Choose "000100", convert "110001001" → "110000001" → "111111111".
 * The final string without augmentation is "1111111". The maximum number of active sections is 7.
 * Example 4:
 * <p>
 * Input: s = "01010"
 * <p>
 * Output: 4
 * <p>
 * Explanation:
 * <p>
 * String "01010" → Augmented to "1010101".
 * Choose "010", convert "1010101" → "1000101" → "1111101".
 * The final string without augmentation is "11110". The maximum number of active sections is 4.
 */
public class MaxActiveSectionWithTradeI {

    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int prevZeros = Integer.MIN_VALUE;
        int i = 0;
        int ones = 0;
        int best = 0;
        while (i < n) {
            int j = i;
            char c = s.charAt(i);
            while (j < n && s.charAt(j) == c) {
                j++;
            }

            int len = j - i;
            if (c == '1') {
                ones += len;
            } else {
                if (prevZeros != Integer.MIN_VALUE) {
                    best = Math.max(best, prevZeros + len);
                }
                prevZeros = len;
            }
            i = j;
        }
        return best + ones;
    }

}
