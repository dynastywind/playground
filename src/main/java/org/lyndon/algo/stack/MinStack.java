package org.lyndon.algo.stack;

import java.util.Stack;

/**
 * LC155
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(int value) pushes the element value onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 */
public class MinStack {

    private final Stack<Integer> stack;

    private final Stack<Integer> min;

    public MinStack() {
        this.stack = new Stack<>();
        this.min = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
        if (min.isEmpty()) {
            min.push(value);
        } else if (min.peek() >= value) {
            min.push(value);
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int value = stack.pop();
        if (min.peek() == value) {
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }

}
