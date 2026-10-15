package org.lyndon.design.lb.strategies;

import org.lyndon.design.lb.Backend;

import java.util.List;

public interface RoutingStrategy {

    String select(List<Backend> nodes, String key);

}
