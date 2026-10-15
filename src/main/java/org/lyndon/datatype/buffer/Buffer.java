package org.lyndon.datatype.buffer;

public interface Buffer<T> {

    T pop();

    void push(T item);

    int count();

    boolean isFull();

}
