package org.lyndon.algo.graph;

/**
 * LC74
 */
public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        return binarySearch(matrix, target, n, 0, m * n - 1);
    }

    private boolean binarySearch(int[][] matrix, int target, int n, int lo, int hi) {
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            int[] coord = toIndex(mid, n);
            int r = coord[0];
            int c = coord[1];
            if (matrix[r][c] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int[] coord = toIndex(lo, n);
        return matrix[coord[0]][coord[1]] == target;
    }

    private int[] toIndex(int v, int n) {
        return new int[]{v / n, v % n};
    }

}
