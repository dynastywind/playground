package org.lyndon.algo.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LC3362
 */
public class ZeroArrayTransformIII {

    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = nums.length;
        int m = queries.length;
        int[] diff = new int[n + 1];
        int sum = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {
            sum += diff[i];
            while (j < m && queries[j][0] <= i) {
                pq.offer(queries[j++][1]);
            }
            while (sum < nums[i]) {
                if (pq.isEmpty() || pq.peek() < i) {
                    return -1;
                }
                int r = pq.poll();
                sum++;
                diff[r + 1]--;
            }
        }
        return pq.size();
    }

}
