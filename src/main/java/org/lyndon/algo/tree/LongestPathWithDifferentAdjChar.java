package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LC2246
 */
public class LongestPathWithDifferentAdjChar {

    private int ans;

    public int longestPath(int[] parent, String s) {
        ans = 1;
        int n = parent.length;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }
        dfs(0, children, s.toCharArray());
        return ans;
    }

    private int dfs(int u, List<Integer>[] children, char[] cs) {
        int max = 0;
        int secMax = 0;
        for (int v : children[u]) {
            int len = dfs(v, children, cs);
            if (cs[v] == cs[u]) {
                continue;
            }
            if (len > max) {
                secMax = max;
                max = len;
            } else if (len > secMax) {
                secMax = len;
            }
        }
        ans = Math.max(ans, 1 + max + secMax);
        return 1 + max;
    }

}
