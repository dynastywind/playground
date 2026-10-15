package org.lyndon.design.lb;

import org.lyndon.design.lb.exception.NoBackendAvailableException;
import org.lyndon.design.lb.strategies.RoutingStrategy;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LoadBalancer {

    private final int capacity;

    private final RoutingStrategy strategy;

    private final Object lock;

    private final Set<Backend> nodes;

    private volatile List<Backend> snapshot;

    public LoadBalancer(RoutingStrategy strategy, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.strategy = strategy;
        this.capacity = capacity;
        this.lock = new Object();
        this.nodes = new LinkedHashSet<>();
        this.snapshot = List.of();
    }

    public boolean register(Backend node) {
        requireNonBlank(node);
        synchronized (lock) {
            if (nodes.contains(node)) {
                return false;
            }
            if (nodes.size() >= capacity) {
                throw new IllegalStateException("Capacity exceeded");
            }
            nodes.add(node);
            snapshot = List.copyOf(nodes);
            return true;
        }
    }

    public boolean unregister(Backend node) {
        requireNonBlank(node);
        synchronized (lock) {
            if (!nodes.remove(node)) {
                return false;
            }
            snapshot = List.copyOf(nodes);
            return true;
        }
    }

    public String get() {
        List<Backend> list = snapshot;
        if (list.isEmpty()) {
            throw new NoBackendAvailableException();
        }
        return strategy.select(list, "");
    }

    public List<Backend> nodes() {
        return snapshot;
    }

    private static void requireNonBlank(Backend node) {
        if (node == null || node.id().isEmpty()) {
            throw new IllegalArgumentException("Node must not be null or empty.");
        }
    }

}
