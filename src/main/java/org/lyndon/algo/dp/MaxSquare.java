package org.lyndon.algo.dp;

/**
 * LC221
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 */
public class MaxSquare {

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int len = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i][j]) + 1;
                } else {
                    dp[i + 1][j + 1] = 0;
                }
                len = Math.max(len, dp[i + 1][j + 1]);
            }
        }
        return len * len;
    }

    public static void main(String[] args) {
        var solution = new MaxSquare();
        System.out.println(solution.maximalSquare(new char[][]{{'0', '1'}}));
    }

}
