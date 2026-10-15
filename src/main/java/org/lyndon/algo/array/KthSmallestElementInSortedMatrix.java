package org.lyndon.algo.array;

/**
 * LC378
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * You must find a solution with a memory complexity better than O(n2).
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * Example 2:
 * <p>
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 */
public class KthSmallestElementInSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];
        while (low < high) {
            int mid = (low + high) >> 1;
            if (check(matrix, n, mid) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int check(int[][] matrix, int n, int target) {
        int i = n - 1;
        int j = 0;
        int cnt = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= target) {
                cnt += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        var solution = new KthSmallestElementInSortedMatrix();
        System.out.println(solution.kthSmallest(new int[][]{{-5, -4}, {-5, -4}}, 2));
    }

}
