package org.lyndon.algo.graph.eulerian;

import java.util.*;

/**
 * LC332
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 * <p>
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * <p>
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 */
public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).offer(ticket.get(1));
        }

        List<String> route = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            String cur = stack.peek();
            PriorityQueue<String> pq = graph.get(cur);
            if (pq == null || pq.isEmpty()) {
                route.addFirst(stack.pop());
            } else {
                stack.push(pq.poll());
            }
        }
        return route;
    }

}
