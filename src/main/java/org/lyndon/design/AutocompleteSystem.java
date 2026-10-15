package org.lyndon.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC642
 */
public class AutocompleteSystem {

    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        List<String> top = new ArrayList<>();
    }

    private final Node root;
    private final Map<String, Integer> cnt;

    private Node cur;
    private StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new Node();
        this.cnt = new HashMap<>();
        this.cur = root;
        this.sb = new StringBuilder();

        for (int i = 0; i < sentences.length; i++) {
            cnt.merge(sentences[i], times[i], Integer::sum);
        }
        for (String sentence : sentences) {
            insert(sentence);
        }
    }

    private void insert(String s) {
        Node cur = root;
        for (char c : s.toCharArray()) {
            cur = cur.children.computeIfAbsent(c, k -> new Node());
            cur.top.remove(s);
            cur.top.add(s);
            cur.top.sort((a, b) -> {
                if (!cnt.get(a).equals(cnt.get(b))) {
                    return Integer.compare(cnt.get(b), cnt.get(a));
                }
                return a.compareTo(b);
            });
            if (cur.top.size() > 3) {
                cur.top.removeLast();
            }
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String s = sb.toString();
            cnt.merge(s, 1, Integer::sum);
            insert(s);
            sb.setLength(0);
            cur = root;
            return List.of();
        }
        sb.append(c);
        if (cur != null) {
            cur = cur.children.get(c);
        }
        return cur == null ? List.of() : cur.top;
    }

}
