package org.lyndon.algo.hash;

import java.util.*;

public class TopKWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        TreeSet<String> set = new TreeSet<>((o1, o2) -> {
            int a = count.get(o1);
            int b = count.get(o2);
            if (a != b) {
                return Integer.compare(b, a);
            }
            return o1.compareTo(o2);
        });
        for (String word : words) {
            count.merge(word, 1, Integer::sum);
        }
        for (String word : count.keySet()) {
            set.add(word);
            if (set.size() > k) {
                set.pollLast();
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        var solution = new TopKWords();
        System.out.println(solution.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(solution.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
    }

}
