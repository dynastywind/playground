package org.lyndon.datatype.buffer;

public class RingBuffer<T> implements Buffer<T> {

    private final T[] buffer;

    private int head;

    private int tail;

    private boolean isFull;

    @SuppressWarnings("unchecked")
    public RingBuffer(int capacity) {
        if ((capacity & (capacity - 1)) != 0) {
            throw new IllegalArgumentException("capacity must be power of 2");
        }
        this.buffer = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.isFull = false;
    }

    @Override
    public T pop() {
        if (!isFull && head == tail) {
            return null;
        }
        var item = buffer[tail];
        tail = (tail + 1) & (buffer.length - 1);
        isFull = false;
        return item;
    }

    @Override
    public void push(T item) {
        buffer[head] = item;
        if (isFull) {
            tail = (tail + 1) & (buffer.length - 1);
        }
        head = (head + 1) & (buffer.length - 1);
        isFull = head == tail;
    }

    //   t
    // h
    // 0 1
    @Override
    public int count() {
        if (isFull) {
            return buffer.length;
        }
        if (head >= tail) {
            return head - tail;
        }
        return buffer.length + head - tail;
    }

    @Override
    public boolean isFull() {
        return isFull;
    }

    public static void main(String[] args) {
        Buffer<Integer> buffer = new RingBuffer<>(2);
        System.out.println(buffer.count());
        System.out.println(buffer.isFull());
        System.out.println(buffer.pop());
        buffer.push(1);
        buffer.push(2);
        buffer.pop();
        System.out.println(buffer.count());
        buffer.push(3);
        System.out.println(buffer.count());
        System.out.println(buffer.pop());
        System.out.println(buffer.count());
    }

}
