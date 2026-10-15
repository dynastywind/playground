package org.lyndon.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * LC465
 */
public class AccountBalancing {

    public int minTransfers(int[][] transactions) {
        int[] balance = new int[12];
        for (int[] transaction : transactions) {
            balance[transaction[0]] -= transaction[2];
            balance[transaction[1]] += transaction[2];
        }
        List<Integer> list = new ArrayList<>();
        for (int b : balance) {
            if (b != 0) {
                list.add(b);
            }
        }
        return dfs(list, 0);
    }

    private int dfs(List<Integer> balances, int start) {
        while (start < balances.size() && balances.get(start) == 0) {
            start++;
        }
        if (start == balances.size()) {
            return 0;
        }

        int best = Integer.MAX_VALUE;
        for (int i = start + 1; i < balances.size(); i++) {
            if ((long) balances.get(start) * balances.get(i) >= 0) {
                continue;
            }
            balances.set(i, balances.get(i) + balances.get(start));
            best = Math.min(best, 1 + dfs(balances, start + 1));
            balances.set(i, balances.get(i) - balances.get(start));
        }
        return best;
    }

}
