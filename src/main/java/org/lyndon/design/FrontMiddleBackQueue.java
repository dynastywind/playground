package org.lyndon.design;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC1670
 */
public class FrontMiddleBackQueue {

    private final Deque<Integer> left = new ArrayDeque<>();
    private final Deque<Integer> right = new ArrayDeque<>();

    public FrontMiddleBackQueue() {
    }

    public void pushFront(int val) {
        left.addFirst(val);
        rebalance();
    }

    public void pushMiddle(int val) {
        if (left.size() == right.size()) {
            right.addFirst(val);
        } else {
            left.addLast(val);
        }
    }

    public void pushBack(int val) {
        right.addLast(val);
        rebalance();
    }

    public int popFront() {
        if (isEmpty()) {
            return -1;
        }
        int res = left.isEmpty() ? right.pollFirst() : left.pollFirst();
        rebalance();
        return res;
    }

    public int popMiddle() {
        if (isEmpty()) {
            return -1;
        }
        int res = (left.size() == right.size() ? left.pollLast() : right.pollFirst());
        rebalance();
        return res;
    }

    public int popBack() {
        if (isEmpty()) {
            return -1;
        }
        int res = right.pollLast();
        rebalance();
        return res;
    }

    private boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }

    private void rebalance() {
        if (left.size() > right.size()) {
            right.addFirst((left.pollLast()));
        } else if (right.size() > left.size() + 1) {
            left.addLast(right.pollFirst());
        }
    }

}
