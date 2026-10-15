package org.lyndon.algo.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val) {
        this(val, null, null);
    }

    public String bfs() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        List<String> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                result.add("" + node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return String.join(",", result);
    }

    @Override
    public String toString() {
        return bfs();
    }

}
