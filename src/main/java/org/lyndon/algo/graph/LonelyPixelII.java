package org.lyndon.algo.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * LC533
 * Given an m x n picture consisting of black 'B' and white 'W' pixels and an integer target, return the number of black lonely pixels.
 * <p>
 * A black lonely pixel is a character 'B' that located at a specific position (r, c) where:
 * <p>
 * Row r and column c both contain exactly target black pixels.
 * For all rows that have a black pixel at column c, they should be exactly the same as row r.
 * <p>
 * Example 1:
 * <p>
 * Input: picture = [["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","W","B","W","B","W"]], target = 3
 * Output: 6
 * Explanation: All the green 'B' are the black pixels we need (all 'B's at column 1 and 3).
 * Take 'B' at row r = 0 and column c = 1 as an example:
 * - Rule 1, row r = 0 and column c = 1 both have exactly target = 3 black pixels.
 * - Rule 2, the rows have black pixel at column c = 1 are row 0, row 1 and row 2. They are exactly the same as row r = 0.
 * <p>
 * Example 2:
 * <p>
 * Input: picture = [["W","W","B"],["W","W","B"],["W","W","B"]], target = 1
 * Output: 0
 */
public class LonelyPixelII {

    public int findBlackPixel(char[][] picture, int target) {
        int m = picture.length;
        int n = picture[0].length;
        Map<String, Integer> rowPatternCnt = new HashMap<>();
        int[] colCnt = new int[n];
        for (int i = 0; i < m; i++) {
            rowPatternCnt.merge(new String(picture[i]), 1, Integer::sum);
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    colCnt[j]++;
                }
            }
        }

        int ans = 0;
        for (Map.Entry<String, Integer> entry : rowPatternCnt.entrySet()) {
            if (entry.getValue() != target) {
                continue;
            }
            String row = entry.getKey();
            int rowCnt = 0;
            for (int j = 0; j < n; j++) {
                if (row.charAt(j) == 'B') {
                    rowCnt++;
                }
            }
            if (rowCnt != target) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (row.charAt(j) == 'B' && colCnt[j] == target) {
                    ans += target;
                }
            }
        }
        return ans;
    }

}
