package org.lyndon.design.lb.strategies;

import org.lyndon.design.lb.Backend;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStrategy implements RoutingStrategy {

    @Override
    public String select(List<Backend> nodes, String key) {
        return nodes.get(ThreadLocalRandom.current().nextInt(nodes.size())).id();
    }

}
