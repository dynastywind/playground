package org.lyndon.algo.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LC2196
 */
public class CreateBinaryTreeFromDesc {

    public TreeNode createBinaryTree(int[][] descriptions) {
        Set<Integer> nonRoot = new HashSet<>();
        Map<Integer, TreeNode> map = new HashMap<>();
        for (int[] description : descriptions) {
            TreeNode node;
            if (map.containsKey(description[0])) {
                node = map.get(description[0]);
            } else {
                node = new TreeNode(description[0]);
                map.put(description[0], node);
            }
            nonRoot.add(description[1]);
            if (description[2] == 0) {
                if (map.containsKey(description[1])) {
                    node.right = map.get(description[1]);
                } else {
                    node.right = new TreeNode(description[1]);
                    map.put(description[1], node.right);
                }
            } else {
                if (map.containsKey(description[1])) {
                    node.left = map.get(description[1]);
                } else {
                    node.left = new TreeNode(description[1]);
                    map.put(description[1], node.left);
                }
            }
        }
        return map.entrySet().stream().filter(entry -> !nonRoot.contains(entry.getKey())).findAny().orElseThrow().getValue();
    }

}
