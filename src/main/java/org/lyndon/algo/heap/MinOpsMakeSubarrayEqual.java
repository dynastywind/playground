package org.lyndon.algo.heap;

import java.util.TreeMap;

/**
 * LC3422
 * You are given an integer array nums and an integer k. You can perform the following operation any number of times:
 * <p>
 * Increase or decrease any element of nums by 1.
 * Return the minimum number of operations required to ensure that at least one subarray of size k in nums has all elements equal.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,-3,2,1,-4,6], k = 3
 * <p>
 * Output: 5
 * <p>
 * Explanation:
 * <p>
 * Use 4 operations to add 4 to nums[1]. The resulting array is [4, 1, 2, 1, -4, 6].
 * Use 1 operation to subtract 1 from nums[2]. The resulting array is [4, 1, 1, 1, -4, 6].
 * The array now contains a subarray [1, 1, 1] of size k = 3 with all elements equal. Hence, the answer is 5.
 * Example 2:
 * <p>
 * Input: nums = [-2,-2,3,1,4], k = 2
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * The subarray [-2, -2] of size k = 2
 */
public class MinOpsMakeSubarrayEqual {

    private static class TwoHeaps {
        // smaller half, including mid
        private final TreeMap<Integer, Integer> min;
        private final TreeMap<Integer, Integer> max;
        private long minSum;
        private long maxSum;
        private int minSize;
        private int maxSize;

        public TwoHeaps() {
            this.min = new TreeMap<>();
            this.max = new TreeMap<>();
            this.minSum = 0;
            this.maxSum = 0;
            this.minSize = 0;
            this.maxSize = 0;
        }

        public void add(int x) {
            if (min.isEmpty() || x <= min.lastKey()) {
                min.merge(x, 1, Integer::sum);
                minSum += x;
                minSize++;
            } else {
                max.merge(x, 1, Integer::sum);
                maxSum += x;
                maxSize++;
            }
            rebalance();
        }

        public void remove(int x) {
            if (min.containsKey(x)) {
                if (min.merge(x, -1, Integer::sum) == 0) {
                    min.remove(x);
                }
                minSum -= x;
                minSize--;
            } else {
                if (max.merge(x, -1, Integer::sum) == 0) {
                    max.remove(x);
                }
                maxSum -= x;
                maxSize--;
            }
            rebalance();
        }

        /**
         * minSize == maxSize or minSize = maxSize + 1
         */
        private void rebalance() {
            while (minSize > maxSize + 1) {
                int x = min.lastKey();
                if (min.merge(x, -1, Integer::sum) == 0) {
                    min.remove(x);
                }
                minSum -= x;
                minSize--;
                max.merge(x, 1, Integer::sum);
                maxSum += x;
                maxSize++;
            }
            while (minSize < maxSize) {
                int x = max.firstKey();
                if (max.merge(x, -1, Integer::sum) == 0) {
                    max.remove(x);
                }
                maxSum -= x;
                maxSize--;
                min.merge(x, 1, Integer::sum);
                minSum += x;
                minSize++;
            }
        }

        public int getMid() {
            return min.lastKey();
        }

        public long getMinSum() {
            return this.minSum;
        }

        public int getMinSize() {
            return this.minSize;
        }

        public long getMaxSum() {
            return this.maxSum;
        }

        public int getMaxSize() {
            return this.maxSize;
        }
    }

    public long minOperations(int[] nums, int k) {
        long ans = Long.MAX_VALUE;
        TwoHeaps heaps = new TwoHeaps();
        for (int i = 0; i < nums.length; i++) {
            heaps.add(nums[i]);
            if (i >= k) {
                heaps.remove(nums[i - k]);
            }
            if (i >= k - 1) {
                int mid = heaps.getMid();
                long cost = (mid * heaps.getMinSize() - heaps.getMinSum()) + (heaps.getMaxSum() - mid * heaps.getMaxSize());
                ans = Math.min(ans, cost);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        var solution = new MinOpsMakeSubarrayEqual();
        System.out.println(solution.minOperations(new int[]{4, -3, 2, 1, -4, 6}, 3));
    }

}
