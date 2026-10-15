package org.lyndon.design.ip;

import org.lyndon.design.IpUtils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class TrieMatcher {

    private static class TrieNode {
        TrieNode[] children;
        String action;
        int prefix;
        long order;

        public TrieNode(long order) {
            this.children = new TrieNode[2];
            this.prefix = -1;
            this.order = order;
        }
    }

    private final TrieNode root = new TrieNode(0);

    private final AtomicLong counter = new AtomicLong(0);

    public void insert(String cidr, String action) {
        String[] parts = cidr.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid cidr: " + cidr);
        }

        int prefix = Integer.parseInt(parts[1]);
        long base = IpUtils.ipToLong(parts[0]) & IpUtils.mask(prefix);

        TrieNode node = root;
        for (int i = 0; i < prefix; i++) {
            int bit = (int) (base >> (31 - i)) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode(counter.incrementAndGet());
            }
            node = node.children[bit];
        }
        node.action = action;
        node.prefix = prefix;
    }

    public Optional<String> match(String ip) {
        long v = IpUtils.ipToLong(ip);

        String action = null;
        long order = -1;
        if (root.action != null) {
            action = root.action;
            order = root.order;
        }
        TrieNode node = root;
        for (int i = 0; i < 32; i++) {
            int bit = (int) (v >> (31 - i)) & 1;
            if (node.children[bit] == null) {
                break;
            }
            node = node.children[bit];
            if (node.action != null && node.order > order) {
                action = node.action;
                order = node.order;
            }
        }
        return Optional.ofNullable(action);
    }

}
