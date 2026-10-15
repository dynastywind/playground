package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC763
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        Map<Character, Integer> lastSeen = new HashMap<>();
        for (int i = 0; i < n; i++) {
            lastSeen.put(s.charAt(i), i);
        }
        int limit = -1;
        int cut = limit;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int pos = lastSeen.get(c);
            if (i > limit) {
                if (limit >= 0) {
                    result.add(limit - cut);
                    cut = limit;
                }
            }
            if (pos > limit) {
                limit = pos;
            }
        }
        result.add(limit - cut);
        return result;
    }

    public static void main(String[] args) {
        var solution = new PartitionLabels();
        PrintUtils.printList(solution.partitionLabels("ababcbacadefegdehijhklij"));
        PrintUtils.printList(solution.partitionLabels("eccbbbbdec"));
    }

}
