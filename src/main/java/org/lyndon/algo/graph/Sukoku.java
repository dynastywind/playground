package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LC37
 */
public class Sukoku {

    private final int[] rows = new int[9];
    private final int[] cols = new int[9];
    private final int[] boxes = new int[9];
    private final List<int[]> empties = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    empties.add(new int[]{i, j});
                } else {
                    flip(i, j, board[i][j] - '1');
                }
            }
        }
        backtrack(board, 0);
    }

    private void flip(int i, int j, int d) {
        rows[i] ^= (1 << d);
        cols[j] ^= (1 << d);
        boxes[i / 3 * 3 + j / 3] ^= (1 << d);
    }

    private boolean backtrack(char[][] board, int pos) {
        if (pos == empties.size()) {
            return true;
        }

        int best = pos;
        int bestCnt = 10;
        for (int k = pos; k < empties.size(); k++) {
            int[] p = empties.get(k);
            int cnt = Integer.bitCount(candidates(p[0], p[1]));
            if (cnt < bestCnt) {
                bestCnt = cnt;
                best = k;
                if (cnt <= 1) {
                    break;
                }
            }
        }
        Collections.swap(empties, pos, best);

        int[] cur = empties.get(pos);
        int i = cur[0];
        int j = cur[1];
        int mask = candidates(i, j);
        while (mask != 0) {
            int d = Integer.numberOfTrailingZeros(mask & -mask);
            flip(i, j, d);
            board[i][j] = (char) (d + '1');
            if (backtrack(board, pos + 1)) {
                return true;
            }
            flip(i, j, d);
            board[i][j] = '.';
            mask &= (mask - 1);
        }
        Collections.swap(empties, pos, best);
        return false;
    }

    private int candidates(int i, int j) {
        return ~(rows[i] | cols[j] | boxes[i / 3 * 3 + j / 3]) & 0x1FF;
    }

}
