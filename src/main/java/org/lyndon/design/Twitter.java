package org.lyndon.design;

import org.lyndon.utils.PrintUtils;

import java.util.*;

/**
 * LC355
 */
public class Twitter {

    private static class Tweet {
        int id;
        long timestamp;

        public Tweet(int id, long timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }

    private static class Wrapper {
        Tweet tweet;
        int i;

        public Wrapper(Tweet tweet, int i) {
            this.tweet = tweet;
            this.i = i;
        }
    }

    private final Map<Integer, Set<Integer>> followees;

    private final Map<Integer, List<Tweet>> tweets;

    private final int limit = 10;

    private int cnt = 0;

    public Twitter() {
        followees = new HashMap<>();
        tweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        List<Tweet> list = tweets.computeIfAbsent(userId, k -> new ArrayList<>(limit));
        if (list.size() == limit) {
            list.removeLast();
        }
        list.addFirst(new Tweet(tweetId, cnt++));
    }

    public List<Integer> getNewsFeed(int userId) {
        List<List<Tweet>> feeds = new ArrayList<>();
        if (tweets.containsKey(userId)) {
            List<Tweet> list = tweets.get(userId);
            if (!list.isEmpty()) {
                feeds.add(list);
            }
        }
        if (followees.containsKey(userId)) {
            feeds.addAll(followees.get(userId).stream().filter(tweets::containsKey).map(tweets::get).filter(t -> !t.isEmpty()).toList());
        }
        PriorityQueue<Wrapper> pq = new PriorityQueue<>((a, b) -> Long.compare(b.tweet.timestamp, a.tweet.timestamp));
        int[] heads = new int[feeds.size()];
        for (int i = 0; i < feeds.size(); i++) {
            pq.offer(new Wrapper(feeds.get(i).get(heads[i]++), i));
        }
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty() && result.size() < limit) {
            Wrapper wrapper = pq.poll();
            result.add(wrapper.tweet.id);
            List<Tweet> queue = feeds.get(wrapper.i);
            if (queue.size() > heads[wrapper.i]) {
                pq.offer(new Wrapper(queue.get(heads[wrapper.i]++), wrapper.i));
            }
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        followees.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followees.containsKey(followerId)) {
            followees.get(followerId).remove(followeeId);
        }
    }

    public static void main(String[] args) {
        var twitter = new Twitter();
        twitter.postTweet(1, 4);
        twitter.postTweet(2, 5);
        twitter.unfollow(1, 2);
        twitter.follow(1, 2);
        PrintUtils.printList(twitter.getNewsFeed(1));
    }

}
