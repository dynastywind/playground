package org.lyndon.design;

/**
 * LC1381
 */
public class CustomStack {

    private final int[] stack;

    private int top;

    public CustomStack(int maxSize) {
        this.stack = new int[maxSize];
        this.top = -1;
    }

    public void push(int x) {
        if (top == stack.length - 1) {
            return;
        }
        stack[++top] = x;
    }

    public int pop() {
        if (top < 0) {
            return -1;
        }
        return stack[top--];
    }

    public void increment(int k, int val) {
        for (int i = 0; i <= Math.min(k - 1, top); i++) {
            stack[i] += val;
        }
    }

}
