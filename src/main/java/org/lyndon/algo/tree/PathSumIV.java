package org.lyndon.algo.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * LC666
 * If the depth of a tree is smaller than 5, then this tree can be represented by an array of three-digit integers. You are given an ascending array nums consisting of three-digit integers representing a binary tree with a depth smaller than 5, where for each integer:
 * <p>
 * The hundreds digit represents the depth d of this node, where 1 <= d <= 4.
 * The tens digit represents the position p of this node within its level, where 1 <= p <= 8, corresponding to its position in a full binary tree.
 * The units digit represents the value v of this node, where 0 <= v <= 9.
 * Return the sum of all paths from the root towards the leaves.
 * <p>
 * It is guaranteed that the given array represents a valid connected binary tree.
 */
public class PathSumIV {

    private int total;

    public int pathSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num / 10, num % 10);
        }
        dfs(map, nums[0] / 10, 0);
        return total;
    }

    private void dfs(Map<Integer, Integer> map, int key, int sum) {
        int depth = key / 10;
        int pos = key % 10;
        sum += map.get(key);

        boolean hasChildren = false;
        int leftKey = (depth + 1) * 10 + (2 * pos - 1);
        if (map.containsKey(leftKey)) {
            hasChildren = true;
            dfs(map, leftKey, sum);
        }
        int rightKey = (depth + 1) * 10 + (2 * pos);
        if (map.containsKey(rightKey)) {
            hasChildren = true;
            dfs(map, rightKey, sum);
        }
        if (!hasChildren) {
            total += sum;
        }
    }

}
