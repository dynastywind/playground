package org.lyndon.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * LC3815
 */
public class AuctionSystem {

    private final Map<Integer, Map<Integer, Integer>> bids;
    private final Map<Integer, TreeMap<Integer, TreeSet<Integer>>> prices;

    public AuctionSystem() {
        this.bids = new HashMap<>();
        this.prices = new HashMap<>();
    }

    public void addBid(int userId, int itemId, int bidAmount) {
        Map<Integer, Integer> bid = bids.computeIfAbsent(userId, k -> new HashMap<>());
        if (bid.containsKey(itemId)) {
            int old = bid.get(itemId);
            TreeMap<Integer, TreeSet<Integer>> itemBids = prices.get(itemId);
            TreeSet<Integer> users = itemBids.get(old);
            users.remove(userId);
            if (users.isEmpty()) {
                itemBids.remove(old);
            }
        }
        bid.put(itemId, bidAmount);
        TreeMap<Integer, TreeSet<Integer>> itemBids = prices.computeIfAbsent(itemId, k -> new TreeMap<>());
        itemBids.computeIfAbsent(bidAmount, k -> new TreeSet<>()).add(userId);
    }

    public void updateBid(int userId, int itemId, int newAmount) {
        addBid(userId, itemId, newAmount);
    }

    public void removeBid(int userId, int itemId) {
        if (!bids.containsKey(userId)) {
            return;
        }
        Map<Integer, Integer> bid = bids.get(userId);
        if (!bid.containsKey(itemId)) {
            return;
        }
        int amt = bid.remove(itemId);
        if (bid.isEmpty()) {
            bids.remove(userId);
        }
        TreeMap<Integer, TreeSet<Integer>> itemBids = prices.get(itemId);
        TreeSet<Integer> userIds = itemBids.get(amt);
        userIds.remove(userId);
        if (userIds.isEmpty()) {
            itemBids.remove(amt);
        }
        if (itemBids.isEmpty()) {
            prices.remove(itemId);
        }
    }

    public int getHighestBidder(int itemId) {
        if (!prices.containsKey(itemId)) {
            return -1;
        }
        return prices.get(itemId).lastEntry().getValue().last();
    }

}
