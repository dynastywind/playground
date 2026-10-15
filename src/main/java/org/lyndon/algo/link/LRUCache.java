package org.lyndon.algo.link;

import java.util.HashMap;
import java.util.Map;

/**
 * LC146
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCache {

    private static class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }

    private final Node head, tail;

    private final Map<Integer, Node> map;

    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNodeFromList(node);
        addNodeToList(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node;
        if (!map.containsKey(key)) {
            if (map.size() == capacity) {
                Node r = tail.prev;
                removeNodeFromList(r);
                map.remove(r.key);
            }
            node = new Node();
            node.key = key;
            node.val = value;
            map.put(key, node);
        } else {
            node = map.get(key);
            node.val = value;
            removeNodeFromList(node);
        }
        addNodeToList(node);
    }

    private void removeNodeFromList(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToList(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        var cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        System.out.println(cache.get(2));
    }

}
