package org.lyndon.algo.tree;

/**
 * LC683
 */
public class KEmptySlots {

    private int[] tree;
    private int n;

    public int kEmptySlots(int[] bulbs, int k) {
        n = bulbs.length;
        tree = new int[n + 1];
        boolean[] on = new boolean[n + 1];
        for (int day = 0; day < n; day++) {
            int x = bulbs[day];
            on[x] = true;
            update(x, 1);

            int y = x - k;
            if (y >= 2 && on[y - 1] && range(x - 1, y) == 0) {
                return day + 1;
            }
            int z = x + k;
            if (z < n && on[z + 1] && range(z, x + 1) == 0) {
                return day + 1;
            }
        }
        return -1;
    }

    private void update(int node, int delta) {
        for (int i = node; i <= n; i += i & (-i)) {
            tree[i] += delta;
        }
    }

    private int query(int node) {
        int ans = 0;
        for (int i = node; i > 0; i -= i & (-i)) {
            ans += tree[i];
        }
        return ans;
    }

    private int range(int x, int y) {
        return query(x) - query(y - 1);
    }

}
