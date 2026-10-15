package org.lyndon.algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * LC3211
 * You are given a positive integer n.
 * <p>
 * A binary string x is valid if all substrings of x of length 2 contain at least one "1".
 * <p>
 * Return all valid strings with length n, in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * <p>
 * Output: ["010","011","101","110","111"]
 * <p>
 * Explanation:
 * <p>
 * The valid strings of length 3 are: "010", "011", "101", "110", and "111".
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1
 * <p>
 * Output: ["0","1"]
 * <p>
 * Explanation:
 * <p>
 * The valid strings of length 1 are: "0" and "1".
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 18
 */
public class GenerateBinaryStringsWithoutAdjacentZeros {

    public List<String> validStrings(int n) {
        List<String> result = new ArrayList<>();
        dfs(0, n, new StringBuilder(), result);
        return result;
    }

    private void dfs(int i, int n, StringBuilder sb, List<String> values) {
        if (i == n) {
            values.add(sb.toString());
            return;
        }
        if (sb.length() == i) {
            sb.append('1');
        } else {
            sb.setCharAt(i, '1');
        }
        dfs(i + 1, n, sb, values);
        if (i == 0 || sb.charAt(i - 1) == '1') {
            sb.setCharAt(i, '0');
            dfs(i + 1, n, sb, values);
        }
    }

}
