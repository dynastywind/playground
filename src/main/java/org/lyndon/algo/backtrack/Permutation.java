package org.lyndon.algo.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutation {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        backtrack(nums, new boolean[nums.length], new LinkedList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtrack(nums, used, path, result);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        var permutation = new Permutation();
        System.out.println(permutation.permute(new int[]{1, 2, 3}));
    }

}
