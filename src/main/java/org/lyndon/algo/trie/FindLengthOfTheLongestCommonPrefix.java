package org.lyndon.algo.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * LC3043
 */
public class FindLengthOfTheLongestCommonPrefix {

    private static class TrieNode {
        TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[10];
        }
    }

    private TrieNode root = new TrieNode();

    private void insert(int x) {
        TrieNode node = root;
        List<Integer> list = toDigits(x);
        for (int n : list) {
            if (node.children[n] == null) {
                node.children[n] = new TrieNode();
            }
            node = node.children[n];
        }
    }

    private int query(int x) {
        TrieNode node = root;
        int len = 0;
        List<Integer> list = toDigits(x);
        for (int n : list) {
            if (node.children[n] == null) {
                break;
            }
            node = node.children[n];
            len++;
        }
        return len;
    }

    private List<Integer> toDigits(int x) {
        List<Integer> list = new ArrayList<>();
        while (x > 0) {
            list.add(x % 10);
            x /= 10;
        }
        return list.reversed();
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        for (int x : arr1) {
            insert(x);
        }

        int ans = 0;
        for (int x : arr2) {
            ans = Math.max(ans, query(x));
        }
        return ans;
    }

}
