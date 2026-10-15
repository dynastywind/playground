package org.lyndon.algo.tree;

/**
 * LC2158
 */
public class AmountOfNewAreaPaintedEveryDay {

    private static final int MAX = 50001;

    public int[] amountPainted(int[][] paint) {
        int[] next = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            next[i] = i;
        }

        int n = paint.length;
        int[] worklog = new int[n];
        for (int i = 0; i < n; i++) {
            int start = paint[i][0];
            int end = paint[i][1];
            int cnt = 0;
            int x = find(next, start);
            while (x < end) {
                cnt++;
                next[x] = x + 1;
                x = find(next, x + 1);
            }
            worklog[i] = cnt;
        }
        return worklog;
    }

    private int find(int[] parent, int x) {
        if (x != parent[x]) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

}
