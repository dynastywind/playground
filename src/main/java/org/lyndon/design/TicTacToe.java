package org.lyndon.design;

import java.util.Random;

/**
 * LC348
 * Databricks enhancement:
 * m * n size with k consecutive marks to win
 * Efficient random move
 */
public class TicTacToe {

    private final int K;
    private final int m;
    private final int n;
    private final int[][] board;
    private final int[][][] len;
    private final int[] pos;
    private final int[] available;
    private final Random random;
    private final int[][] dirs = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

    private int availableSize;
    private int winner;

    public TicTacToe(int n) {
        this.K = n;
        this.m = n;
        this.n = n;
        this.board = new int[m][n];
        this.len = new int[m][n][4];
        this.availableSize = m * n;
        this.pos = new int[availableSize];
        this.available = new int[availableSize];
        this.random = new Random();

        for (int i = 0; i < availableSize; i++) {
            available[i] = i;
            pos[i] = i;
        }
    }

    /*
        x . . .
        x x x o
        . o o o
        . . . .
     */
    public int move(int row, int col, int player) {
        if (row < 0 || row >= m || col < 0 || col >= n || board[row][col] != 0) {
            return -1;
        }
        board[row][col] = player;
        removeFromAvailable(row * n + col);
        for (int i = 0; i < dirs.length; i++) {
            int dr = dirs[i][0];
            int dc = dirs[i][1];
            int forward = len(row + dr, col + dc, i, player);
            int backward = len(row - dr, col - dc, i, player);
            int total = forward + backward + 1;
            len[row + forward * dr][col + forward * dc][i] = total;
            len[row - backward * dr][col - backward * dc][i] = total;
            if (total >= K) {
                winner = player;
                return player;
            }
        }
        return 0;
    }

    private int len(int r, int c, int dir, int player) {
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return 0;
        }
        return board[r][c] == player ? len[r][c][dir] : 0;
    }

    public int[] getNextMove(int player) {
        if (winner != 0 || availableSize == 0) {
            return new int[]{-1, -1};
        }
        int index = random.nextInt(availableSize - 1);
        int cell = available[index];
        return new int[]{cell / n, cell % n};
    }

    private void removeFromAvailable(int cell) {
        int index = pos[cell];
        int last = available[--availableSize];
        available[index] = last;
        pos[last] = index;
    }

}
