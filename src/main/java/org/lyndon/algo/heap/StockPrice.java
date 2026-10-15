package org.lyndon.algo.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * LC2034
 */
public class StockPrice {

    private final Map<Integer, Integer> prices;
    private final TreeMap<Integer, Integer> cnt;

    private int currentTs;

    public StockPrice() {
        this.prices = new HashMap<>();
        this.cnt = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        Integer prev = prices.put(timestamp, price);
        if (prev != null) {
            if (cnt.merge(prev, -1, Integer::sum) == 0) {
                cnt.remove(prev);
            }
        }
        cnt.merge(price, 1, Integer::sum);
        currentTs = Math.max(currentTs, timestamp);
    }

    public int current() {
        return prices.get(currentTs);
    }

    public int maximum() {
        return cnt.lastKey();
    }

    public int minimum() {
        return cnt.firstKey();
    }

}
