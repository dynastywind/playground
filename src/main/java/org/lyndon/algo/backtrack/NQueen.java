package org.lyndon.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LC51
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * <p>
 * Example 1:
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 *
 */
public class NQueen {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new LinkedList<>();
        boolean[][] board = new boolean[n][n];
        boolean[] col = new boolean[n];
        boolean[] diag = new boolean[2 * n - 1];
        boolean[] secDiag = new boolean[2 * n - 1];
        backtrack(board, n, col, diag, secDiag, 0, result);
        return result;
    }

    private void backtrack(boolean[][] board, int n, boolean[] col, boolean[] diag, boolean[] secDiag, int r, List<List<String>> result) {
        if (r == n) {
            List<String> list = new ArrayList<>(n);
            for (boolean[] row : board) {
                StringBuilder sb = new StringBuilder();
                for (boolean cell : row) {
                    if (cell) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            result.add(list);
            return;
        }
        for (int c = 0; c < n; c++) {
            if (col[c] || diag[r - c + n - 1] || secDiag[r + c]) {
                continue;
            }
            board[r][c] = true;
            col[c] = true;
            diag[r - c + n - 1] = true;
            secDiag[r + c] = true;
            backtrack(board, n, col, diag, secDiag, r + 1, result);
            board[r][c] = false;
            col[c] = false;
            diag[r - c + n - 1] = false;
            secDiag[r + c] = false;
        }
    }

}
