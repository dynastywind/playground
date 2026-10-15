package org.lyndon.algo.backtrack;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC40
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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

            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            cur.add(candidates[j]);
            dfs(candidates, j + 1, target - candidates[j], cur, result);
            cur.removeLast();
        }
    }

    public static void main(String[] args) {
        var solution = new CombinationSumII();
        PrintUtils.printLists(solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        PrintUtils.printLists(solution.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

}
