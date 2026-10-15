package org.lyndon.design.lb.strategies;

import org.lyndon.design.lb.Backend;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinStrategy implements RoutingStrategy {

    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public String select(List<Backend> nodes, String key) {
        int i = index.getAndIncrement();
        return nodes.get(Math.floorMod(i, nodes.size())).id();
    }

}
