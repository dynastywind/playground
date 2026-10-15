package org.lyndon.algo.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * LC437
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 */
public class PathSumIII {

    private int count;

    private Map<Long, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, 0);
        return count;
    }

    private void dfs(TreeNode node, int target, long sum) {
        if (node == null) {
            return;
        }
        sum += node.val;
        if (sum == target) {
            count++;
        }
        count += map.getOrDefault(sum - target, 0);
        map.merge(sum, 1, Integer::sum);
        dfs(node.left, target, sum);
        dfs(node.right, target, sum);
        map.merge(sum, -1, Integer::sum);
    }

}
