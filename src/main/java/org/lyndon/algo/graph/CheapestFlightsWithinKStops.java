package org.lyndon.algo.graph;

import java.util.Arrays;

/**
 * LC787 Medium
 * <p>
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 * <p>
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 */
public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < k + 1; i++) {
            int[] cloned = Arrays.copyOf(dist, n);
            boolean updated = false;
            for (int[] flight : flights) {
                int u = flight[0];
                if (cloned[u] == Integer.MAX_VALUE) {
                    continue;
                }
                int v = flight[1];
                int w = flight[2];
                int nw = cloned[u] + w;
                if (nw < dist[v]) {
                    dist[v] = nw;
                    updated = true;
                }
            }
            if (!updated) {
                break;
            }
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

}
