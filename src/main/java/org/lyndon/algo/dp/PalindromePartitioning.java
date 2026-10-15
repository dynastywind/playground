package org.lyndon.algo.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LC131
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        char[] cs = s.toCharArray();
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (cs[i] == cs[j] && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        List<List<String>> ans = new ArrayList<>();
        backtrack(0, n, cs, dp, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int start, int n, char[] cs, boolean[][] dp, List<String> path, List<List<String>> ans) {
        if (start == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < n; i++) {
            if (!dp[start][i]) {
                continue;
            }
            path.add(new String(cs, start, i - start + 1));
            backtrack(i + 1, n, cs, dp, path, ans);
            path.removeLast();
        }
    }

}
