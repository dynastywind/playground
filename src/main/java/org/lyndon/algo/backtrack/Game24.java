package org.lyndon.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * LC679
 */
public class Game24 {

    private static final double EPS = 1e-6;

    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>(cards.length);
        for (int card : cards) {
            list.add((double) card);
        }
        return dfs(list);
    }

    private boolean dfs(List<Double> nums) {
        int n = nums.size();
        if (n == 1) {
            return Math.abs(nums.getFirst() - 24) < EPS;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        next.add(nums.get(k));
                    }
                }

                for (int op = 0; op < 4; op++) {
                    double val;
                    switch (op) {
                        case 0 -> val = nums.get(i) + nums.get(j);
                        case 1 -> val = nums.get(i) * nums.get(j);
                        case 2 -> val = nums.get(i) - nums.get(j);
                        default -> {
                            if (Math.abs(nums.get(j)) < EPS) {
                                continue;
                            }
                            val = nums.get(i) / nums.get(j);
                        }
                    }
                    next.add(val);
                    if (dfs(next)) {
                        return true;
                    }
                    next.removeLast();
                }
            }
        }
        return false;
    }

}
