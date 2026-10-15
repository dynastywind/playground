package org.lyndon.algo.graph;

/**
 * LC240
 */
public class Search2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        return search(matrix, m, n, target, 0, 0, m - 1, n - 1);
    }

    private boolean search(int[][] matrix, int m, int n, int target, int sr, int sc, int er, int ec) {
        if (sr < 0 || sc < 0 || er < 0 || ec < 0 || er >= m || ec >= n || sr > er || sc > ec) {
            return false;
        }
        if (sr == er && sc == ec) {
            return matrix[sr][sc] == target;
        }
        int mr = (sr + er) >>> 1;
        int mc = (sc + ec) >>> 1;
        if (matrix[mr][mc] == target) {
            return true;
        } else if (matrix[mr][mc] > target) {
            return search(matrix, m, n, target, sr, sc, mr, mc) || search(matrix, m, n, target, sr, mc + 1, mr - 1, ec) || search(matrix, m, n, target, mr + 1, sc, er, mc - 1);
        } else {
            return search(matrix, m, n, target, sr, mc + 1, mr, ec) || search(matrix, m, n, target, mr + 1, sc, er, ec);
        }
    }

    public static void main(String[] args) {
        var search2DMatrixII = new Search2DMatrixII();
        System.out.println(search2DMatrixII.searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 20));
        System.out.println(search2DMatrixII.searchMatrix(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}}, 5));
    }

}
