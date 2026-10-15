package org.lyndon.algo.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LC295
 * <p>
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 * <p>
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * <p>
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 */
public class MedianFinder {

    private final PriorityQueue<Integer> max;

    private final PriorityQueue<Integer> min;

    public MedianFinder() {
        this.max = new PriorityQueue<>(Comparator.reverseOrder());
        this.min = new PriorityQueue<>();
    }

    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (min.size() > max.size()) {
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        if (max.size() > min.size()) {
            return max.peek();
        }
        if (max.isEmpty()) {
            return 0;
        }
        return (max.peek() + min.peek()) / 2.0;
    }

}
