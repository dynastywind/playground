package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * LC78
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int index, List<Integer> cur, List<List<Integer>> res) {
        res.add(new ArrayList<>(cur));
        if (index == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            backtrack(nums, i + 1, cur, res);
            cur.removeLast();
        }
    }

    public static void main(String[] args) {
        var solution = new Subsets();
        PrintUtils.printLists(solution.subsets(new int[]{0}));
    }

}
