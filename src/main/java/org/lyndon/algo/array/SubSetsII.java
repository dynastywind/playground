package org.lyndon.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC90
 */
public class SubSetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), subsets);
        return subsets;
    }

    private void backtrack(int[] nums, int index, List<Integer> subset, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(subset));
        if (index == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            backtrack(nums, i + 1, subset, subsets);
            subset.removeLast();
        }
    }

}
