package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LC1506
 */
public class FindRootOfNAryTree {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public Node findRoot(List<Node> tree) {
        Set<Integer> nonRoot = new HashSet<>();
        for (Node node : tree) {
            for (Node child : node.children) {
                nonRoot.add(child.val);
            }
        }
        for (Node node : tree) {
            if (nonRoot.contains(node.val)) {
                continue;
            }
            return node;
        }
        return null;
    }

    public Node findRoot2(List<Node> tree) {
        int root = 0;
        for (Node node : tree) {
            root ^= node.val;
            for (Node child : node.children) {
                root ^= child.val;
            }
        }
        for (Node node : tree) {
            if (node.val == root) {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        var solution = new FindRootOfNAryTree();
        Node root = new Node(1, List.of(new Node(3, List.of(new Node(5), new Node(6))), new Node(2), new Node(4)));
//        System.out.println(solution.findRoot(List.of(root)).val);
        System.out.println(solution.findRoot2(List.of(root)).val);
    }

}
