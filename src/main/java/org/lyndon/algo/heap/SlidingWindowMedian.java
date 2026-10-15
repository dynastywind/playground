package org.lyndon.algo.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * LC480
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 * <p>
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 */
public class SlidingWindowMedian {

    private static class TwoHeap {

        private final TreeMap<Integer, Integer> min;

        private final TreeMap<Integer, Integer> max;

        private int minSize;

        private int maxSize;

        public TwoHeap() {
            this.min = new TreeMap<>();
            this.max = new TreeMap<>();
            this.minSize = 0;
            this.maxSize = 0;
        }

        public void add(int x) {
            if (min.isEmpty() || min.lastKey() >= x) {
                min.merge(x, 1, Integer::sum);
                minSize++;
            } else {
                max.merge(x, 1, Integer::sum);
                maxSize++;
            }
            rebalance();
        }

        public void remove(int x) {
            if (min.containsKey(x)) {
                if (min.merge(x, -1, Integer::sum) == 0) {
                    min.remove(x);
                }
                minSize--;
            } else {
                if (max.merge(x, -1, Integer::sum) == 0) {
                    max.remove(x);
                }
                maxSize--;
            }
            rebalance();
        }

        public Double getMed() {
            if (min.isEmpty()) {
                return null;
            }
            if (minSize == maxSize) {
                return ((long) min.lastKey() + max.firstKey()) / 2.0;
            }
            return (double) min.lastKey();
        }

        private void rebalance() {
            while (minSize > maxSize + 1) {
                int x = min.lastKey();
                if (min.merge(x, -1, Integer::sum) == 0) {
                    min.remove(x);
                }
                minSize--;
                max.merge(x, 1, Integer::sum);
                maxSize++;
            }
            while (minSize < maxSize) {
                int x = max.firstKey();
                if (max.merge(x, -1, Integer::sum) == 0) {
                    max.remove(x);
                }
                maxSize--;
                min.merge(x, 1, Integer::sum);
                minSize++;
            }
        }

    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        TwoHeap heap = new TwoHeap();
        List<Double> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
            if (i >= k) {
                heap.remove(nums[i - k]);
            }
            if (i >= k - 1) {
                ans.add(heap.getMed());
            }
        }
        return ans.stream().mapToDouble(Double::doubleValue).toArray();
    }

}
