package org.lyndon.algo.link;

import java.util.HashMap;
import java.util.Map;

/**
 * LC460
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 * <p>
 * Implement the LFUCache class:
 * <p>
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 * <p>
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 * <p>
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LFUCache {

    private static class Node {
        int key;
        int val;
        int freq;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    private static class DoubleLinkedList {
        private final Node head, tail;
        private int size;

        public DoubleLinkedList() {
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = node.prev = null;
            size--;
        }

        public Node removeLast() {
            if (size == 0) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    private final int capacity;

    private final Map<Integer, Node> cache;

    private final Map<Integer, DoubleLinkedList> freqMap;

    private int leastFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.freqMap = new HashMap<>();
    }

    /*
        O(1)
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        int freq = node.freq;
        DoubleLinkedList items = freqMap.get(freq);
        items.remove(node);
        if (items.isEmpty()) {
            if (node.freq == leastFreq) {
                leastFreq++;
            }
            freqMap.remove(freq);
        }
        freqMap.computeIfAbsent(freq + 1, k -> new DoubleLinkedList()).addFirst(node);
        node.freq++;
        return node.val;
    }

    /*
        O(1)
     */
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            int freq = node.freq;
            node.freq++;
            DoubleLinkedList items = freqMap.get(freq);
            items.remove(node);
            if (items.isEmpty()) {
                if (freq == leastFreq) {
                    leastFreq++;
                }
                freqMap.remove(freq);
            }
            freqMap.computeIfAbsent(freq + 1, k -> new DoubleLinkedList()).addFirst(node);
            cache.put(key, node);
        } else {
            Node node = new Node(key, value);
            if (cache.size() == capacity) {
                DoubleLinkedList items = freqMap.get(leastFreq);
                Node last = items.removeLast();
                cache.remove(last.key);
                if (items.isEmpty()) {
                    freqMap.remove(leastFreq);
                }
            }
            freqMap.computeIfAbsent(1, k -> new DoubleLinkedList()).addFirst(node);
            leastFreq = 1;
            cache.put(key, node);
        }
    }

}
