package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BSTIteratorII implements Iterator<Integer> {

    private final List<Integer> nums;

    private int i = -1;

    public BSTIteratorII(TreeNode root) {
        this.nums = new ArrayList<>();
        dfs(root);
    }

    @Override
    public boolean hasNext() {
        return i < nums.size() - 1;
    }

    public boolean hasPrev() {
        return i > 0;
    }

    @Override
    public Integer next() {
        return nums.get(++i);
    }

    public Integer prev() {
        return nums.get(--i);
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        nums.add(node.val);
        dfs(node.right);
    }

}
