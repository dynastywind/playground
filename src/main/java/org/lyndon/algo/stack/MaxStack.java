package org.lyndon.algo.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * LC716
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 * <p>
 * Implement the MaxStack class:
 * <p>
 * MaxStack() Initializes the stack object.
 * void push(int x) Pushes element x onto the stack.
 * int pop() Removes the element on top of the stack and returns it.
 * int top() Gets the element on the top of the stack without removing it.
 * int peekMax() Retrieves the maximum element in the stack without removing it.
 * int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
 * You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.
 */
public class MaxStack {

    private static class Node {
        int val;
        Node prev;
        Node next;
    }

    private final TreeMap<Integer, List<Node>> map;
    private final Node head;
    private final Node tail;

    public MaxStack() {
        map = new TreeMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void push(int x) {
        Node node = new Node();
        node.val = x;
        addNodeToList(node);
        map.computeIfAbsent(x, k -> new ArrayList<>()).add(node);
    }

    public int pop() {
        if (map.isEmpty()) {
            return -1;
        }
        Node node = tail.prev;
        List<Node> l = map.get(node.val);
        l.removeLast();
        if (l.isEmpty()) {
            map.remove(node.val);
        }
        removeNodeFromList(node);
        return node.val;
    }

    public int top() {
        if (map.isEmpty()) {
            return -1;
        }
        return tail.prev.val;
    }

    public int peekMax() {
        if (map.isEmpty()) {
            return -1;
        }
        return map.lastKey();
    }

    public int popMax() {
        if (map.isEmpty()) {
            return -1;
        }
        Node node = map.lastEntry().getValue().removeLast();
        if (map.get(node.val).isEmpty()) {
            map.remove(node.val);
        }
        removeNodeFromList(node);
        return node.val;
    }

    private void addNodeToList(Node node) {
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev = node;
    }

    private void removeNodeFromList(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}
