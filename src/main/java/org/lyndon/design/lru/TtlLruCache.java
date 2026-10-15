package org.lyndon.design.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TtlLruCache<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        long expireAt;
        Node<K, V> prev, next;
    }

    private final int capacity;
    private final long ttlNanos;

    private final Map<K, Node<K, V>> map = new HashMap<>();
    private final Node<K, V> head = new Node<>();
    private final Node<K, V> tail = new Node<>();
    private final ReentrantLock lock = new ReentrantLock();

    public TtlLruCache(int capacity, long ttl, TimeUnit unit) {
        this.capacity = capacity;
        this.ttlNanos = unit.toNanos(ttl);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        lock.lock();
        try {
            Node<K, V> node = map.get(key);
            if (node == null) {
                return null;
            }
            if (System.nanoTime() > node.expireAt) {
                unlink(node);
                map.remove(key);
                return null;
            }
            moveToFront(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            Node<K, V> node = map.get(key);
            if (node != null) {
                node.value = value;
                node.expireAt = System.nanoTime() + ttlNanos;
                moveToFront(node);
                return;
            }
            node = new Node<>();
            node.key = key;
            node.value = value;
            node.expireAt = System.nanoTime() + ttlNanos;
            map.put(key, node);
            linkFront(node);
            if (map.size() > capacity) {
                Node<K, V> toRemove = tail.prev;
                unlink(toRemove);
                map.remove(toRemove.key);
            }
        } finally {
            lock.unlock();
        }
    }

    private void moveToFront(Node<K, V> n) {
        unlink(n);
        linkFront(n);
    }

    private void linkFront(Node<K, V> n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    private void unlink(Node<K, V> n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

}
