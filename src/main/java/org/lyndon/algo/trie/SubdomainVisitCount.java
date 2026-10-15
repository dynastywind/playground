package org.lyndon.algo.trie;

import java.util.*;

/**
 * LC811
 * A website domain "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com" and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
 * <p>
 * A count-paired domain is a domain that has one of the two formats "rep d1.d2.d3" or "rep d1.d2" where rep is the number of visits to the domain and d1.d2.d3 is the domain itself.
 * <p>
 * For example, "9001 discuss.leetcode.com" is a count-paired domain that indicates that discuss.leetcode.com was visited 9001 times.
 * Given an array of count-paired domains cpdomains, return an array of the count-paired domains of each subdomain in the input. You may return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: cpdomains = ["9001 discuss.leetcode.com"]
 * Output: ["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
 * Explanation: We only have one website domain: "discuss.leetcode.com".
 * As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.
 * Example 2:
 * <p>
 * Input: cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 * Explanation: We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times.
 * For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
 */
public class SubdomainVisitCount {

    private static class TrieNode {
        private final Map<String, TrieNode> children;
        private int cnt;

        public TrieNode() {
            this.children = new HashMap<>();
            this.cnt = 0;
        }

        public void insert(String desc) {
            String[] part = desc.split(" ");
            int cnt = Integer.parseInt(part[0]);
            String[] subDomains = part[1].split("\\.");
            TrieNode node = this;
            for (int i = subDomains.length - 1; i >= 0; i--) {
                String key = subDomains[i];
                node = node.children.computeIfAbsent(key, k -> new TrieNode());
                node.cnt += cnt;
            }
        }

        public List<String> report() {
            List<String> report = new LinkedList<>();
            dfs(this, new ArrayDeque<>(), report);
            return report;
        }

        private void dfs(TrieNode node, Deque<String> path, List<String> result) {
            for (Map.Entry<String, TrieNode> e : node.children.entrySet()) {
                path.addFirst(e.getKey());
                result.add(String.format("%d %s", e.getValue().cnt, String.join(".", path)));
                dfs(e.getValue(), path, result);
                path.removeFirst();
            }
        }
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        TrieNode node = new TrieNode();
        for (String domain : cpdomains) {
            node.insert(domain);
        }
        return node.report();
    }

    public static void main(String[] args) {
        var solution = new SubdomainVisitCount();
        solution.subdomainVisits(new String[]{"9001 discuss.leetcode.com"}).forEach(System.out::println);
        solution.subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}).forEach(System.out::println);
    }

}
