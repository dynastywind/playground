package org.lyndon.design;

import java.util.ArrayDeque;

/**
 * LC232
 */
public class MyQueue {

    private final ArrayDeque<Integer> stack;
    private final ArrayDeque<Integer> backup;

    public MyQueue() {
        this.stack = new ArrayDeque<>();
        this.backup = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        if (!backup.isEmpty()) {
            return backup.pop();
        }
        while (stack.size() > 1) {
            backup.push(stack.pop());
        }
        return stack.pop();
    }

    public int peek() {
        if (!backup.isEmpty()) {
            return backup.peek();
        }
        while (!stack.isEmpty()) {
            backup.push(stack.pop());
        }
        return backup.peek();
    }

    public boolean empty() {
        return stack.isEmpty() && backup.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        System.out.println(myQueue.pop());
        myQueue.push(5);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }

}
