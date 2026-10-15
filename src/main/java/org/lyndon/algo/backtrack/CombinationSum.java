package org.lyndon.algo.backtrack;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC39
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * <p>
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 * <p>
 * Input: candidates = [2], target = 1
 * Output: []
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void dfs(int[] candidates, int i, int target, List<Integer> cur, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if (candidates[j] > target) {
                break;
            }
            cur.add(candidates[j]);
            dfs(candidates, j, target - candidates[j], cur, result);
            cur.removeLast();
        }
    }

    public static void main(String[] args) {
        var solution = new CombinationSum();
        PrintUtils.printLists(solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
        PrintUtils.printLists(solution.combinationSum(new int[]{2, 3, 5}, 8));
    }

}
