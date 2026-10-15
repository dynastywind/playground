package org.lyndon.algo.graph;

/**
 * LC419
 */
public class BattleshipsInABoard {

    private int ans = 0;

    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 'X' || visited[i][j]) {
                    continue;
                }
                ans++;
                dfs(i, j, m, n, board, visited);
            }
        }
        return ans;
    }

    private void dfs(int r, int c, int m, int n, char[][] board, boolean[][] visited) {
        visited[r][c] = true;
        if (r < m - 1 && board[r + 1][c] == 'X') {
            dfs(r + 1, c, m, n, board, visited);
        } else if (c < n - 1 && board[r][c + 1] == 'X') {
            dfs(r, c + 1, m, n, board, visited);
        }
    }

    public static void main(String[] args) {
        var solution = new BattleshipsInABoard();
        System.out.println(solution.countBattleships(new char[][]{{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}}));
    }

}
