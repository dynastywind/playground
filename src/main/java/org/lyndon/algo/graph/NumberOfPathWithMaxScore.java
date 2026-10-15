package org.lyndon.algo.graph;

import java.util.Arrays;
import java.util.List;

/**
 * LC1301
 * You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
 * <p>
 * You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
 * <p>
 * Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
 * <p>
 * In case there is no path, return [0, 0].
 * <p>
 * Example 1:
 * <p>
 * Input: board = ["E23","2X2","12S"]
 * Output: [7,1]
 * Example 2:
 * <p>
 * Input: board = ["E12","1X1","21S"]
 * Output: [4,2]
 * Example 3:
 * <p>
 * Input: board = ["E11","XXX","11S"]
 * Output: [0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= board.length == board[i].length <= 100
 */
public class NumberOfPathWithMaxScore {

    private static final int MOD = 1_000_000_007;

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][] scores = new int[n][n];
        for (int[] score : scores) {
            Arrays.fill(score, -1);
        }
        scores[n - 1][n - 1] = 0;
        int[][] paths = new int[n][n];
        paths[n - 1][n - 1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c = board.get(i).charAt(j);
                if (c == 'X' || c == 'S') {
                    continue;
                }

                relax(i, j, i + 1, j, n, scores, paths);
                relax(i, j, i, j + 1, n, scores, paths);
                relax(i, j, i + 1, j + 1, n, scores, paths);

                if (c == 'E' || scores[i][j] == -1) {
                    continue;
                }
                scores[i][j] += c - '0';
            }
        }
        return scores[0][0] == -1 ? new int[]{0, 0} : new int[]{scores[0][0], paths[0][0]};
    }

    private void relax(int x, int y, int ox, int oy, int n, int[][] scores, int[][] paths) {
        if (ox < 0 || ox >= n || oy < 0 || oy >= n || scores[ox][oy] == -1) {
            return;
        }
        if (scores[x][y] < scores[ox][oy]) {
            scores[x][y] = scores[ox][oy];
            paths[x][y] = paths[ox][oy];
        } else if (scores[x][y] == scores[ox][oy]) {
            paths[x][y] = (paths[x][y] + paths[ox][oy]) % MOD;
        }
    }

}
