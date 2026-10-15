package org.lyndon.design;

import java.util.*;

/**
 * LC1912
 */
public class MovieRentingSystem {

    private static final int TOP = 5;

    // movie -> shop -> price
    private final Map<Integer, Map<Integer, Integer>> prices;
    // movie -> price -> {shop}
    private final Map<Integer, TreeMap<Integer, TreeSet<Integer>>> unrented;
    // price -> shop -> {movie}
    private final TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> rented;

    public MovieRentingSystem(int n, int[][] entries) {
        this.prices = new HashMap<>();
        this.unrented = new HashMap<>();
        this.rented = new TreeMap<>();
        for (int[] entry : entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];
            prices.computeIfAbsent(movie, k -> new TreeMap<>()).put(shop, price);
            unrented.computeIfAbsent(movie, k -> new TreeMap<>()).computeIfAbsent(price, k -> new TreeSet<>()).add(shop);
        }
    }

    public List<Integer> search(int movie) {
        if (!unrented.containsKey(movie)) {
            return Collections.emptyList();
        }
        TreeMap<Integer, TreeSet<Integer>> prices = unrented.get(movie);
        int cnt = 0;
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<Integer>> integerTreeSetEntry : prices.entrySet()) {
            TreeSet<Integer> shops = integerTreeSetEntry.getValue();
            for (int shop : shops) {
                result.add(shop);
                cnt++;
                if (cnt >= TOP) {
                    return result;
                }
            }
        }
        return result;
    }

    public void rent(int shop, int movie) {
        if (!unrented.containsKey(movie)) {
            return;
        }
        int price = prices.get(movie).get(shop);
        unrented.get(movie).get(price).remove(shop);
        rented.computeIfAbsent(price, k -> new TreeMap<>()).computeIfAbsent(shop, k -> new TreeSet<>()).add(movie);
    }

    public void drop(int shop, int movie) {
        if (!prices.containsKey(movie)) {
            return;
        }
        int price = prices.get(movie).get(shop);
        rented.get(price).get(shop).remove(movie);
        unrented.get(movie).computeIfAbsent(price, k -> new TreeSet<>()).add(shop);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        int cnt = 0;
        for (TreeMap<Integer, TreeSet<Integer>> shops : rented.values()) {
            for (Map.Entry<Integer, TreeSet<Integer>> entry : shops.entrySet()) {
                for (int movie : entry.getValue()) {
                    result.add(List.of(entry.getKey(), movie));
                    cnt++;
                    if (cnt >= TOP) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

}
