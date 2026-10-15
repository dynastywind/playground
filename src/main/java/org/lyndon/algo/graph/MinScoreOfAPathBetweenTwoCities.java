package org.lyndon.algo.graph;

/**
 * LC2492
 */
public class MinScoreOfAPathBetweenTwoCities {

    public int minScore(int n, int[][] roads) {
        int[] parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] road : roads) {
            union(parent, road[0], road[1]);
        }

        int ans = Integer.MAX_VALUE;
        int root = find(parent, 1);
        for (int[] road : roads) {
            if (find(parent, road[0]) == root) {
                ans = Math.min(ans, road[2]);
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int[] parent, int a, int b) {
        int ra = find(parent, a);
        int rb = find(parent, b);
        if (ra != rb) {
            parent[rb] = ra;
        }
    }

}
