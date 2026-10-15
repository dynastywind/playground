package org.lyndon.design;

/**
 * LC622
 */
public class MyCircularQueue {

    private final int k;

    private final int[] arr;

    private int head, tail;

    private boolean isFull;

    public MyCircularQueue(int k) {
        this.k = k;
        this.arr = new int[k];
    }

    public boolean enQueue(int value) {
        if (isFull) {
            return false;
        }
        arr[tail] = value;
        tail = (tail + 1) % k;
        if (tail == head) {
            isFull = true;
        }
        return true;
    }

    public boolean deQueue() {
        if (!isFull && tail == head) {
            return false;
        }
        head = (head + 1) % k;
        if (isFull) {
            isFull = false;
        }
        return true;
    }

    public int Front() {
        if (!isFull && tail == head) {
            return -1;
        }
        return arr[head];
    }

    public int Rear() {
        if (!isFull && tail == head) {
            return -1;
        }
        return arr[Math.floorMod(tail - 1, k)];
    }

    public boolean isEmpty() {
        return !isFull && head == tail;
    }

    public boolean isFull() {
        return isFull;
    }

    public static void main(String[] args) {
        var queue = new MyCircularQueue(2);
        System.out.println(queue.isFull());
        queue.enQueue(1);
        queue.enQueue(2);
        System.out.println(queue.Front());
        System.out.println(queue.Rear());
        System.out.println(queue.isFull());
        System.out.println(queue.enQueue(3));
        queue.deQueue();
        System.out.println(queue.Front());
        System.out.println(queue.Rear());
        System.out.println(queue.enQueue(3));
        System.out.println(queue.isFull());
        System.out.println(queue.isEmpty());
        queue.deQueue();
        queue.deQueue();
        System.out.println(queue.isEmpty());
        queue.deQueue();
    }

}
