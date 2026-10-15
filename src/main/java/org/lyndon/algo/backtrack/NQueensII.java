package org.lyndon.algo.backtrack;

/**
 * LC52
 */
public class NQueensII {

    private int n;
    private boolean[] cols;
    private boolean[] diag;
    private boolean[] secDiag;

    public int totalNQueens(int n) {
        this.n = n;
        this.cols = new boolean[n];
        this.diag = new boolean[2 * n - 1];
        this.secDiag = new boolean[2 * n - 1];
        return backtrack(0);
    }

    private int backtrack(int row) {
        if (row == n) {
            return 1;
        }
        int cnt = 0;
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;
            if (cols[col] || diag[d1] || secDiag[d2]) {
                continue;
            }
            cols[col] = true;
            diag[d1] = true;
            secDiag[d2] = true;
            cnt += backtrack(row + 1);
            cols[col] = false;
            diag[d1] = false;
            secDiag[d2] = false;
        }
        return cnt;
    }

}
