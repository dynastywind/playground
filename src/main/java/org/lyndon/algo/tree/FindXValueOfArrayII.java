package org.lyndon.algo.tree;

import java.util.Arrays;

/**
 * LC3525
 */
public class FindXValueOfArrayII {

    private int k;
    private int[] prod;
    private int[][] cnt;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        this.k = k;
        prod = new int[n << 2];
        cnt = new int[n << 2][k];
        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            update(1, 0, n - 1, idx, val);
            int[][] res = query(1, 0, n - 1, start, n - 1);
            ans[i] = res[1][x];
        }
        return ans;
    }

    private void setLeaf(int node, int val) {
        int v = val % k;
        prod[node] = v;
        Arrays.fill(cnt[node], 0);
        cnt[node][v] = 1;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            setLeaf(node, nums[l]);
            return;
        }
        int mid = (l + r) >> 1;
        build(node << 1, l, mid, nums);
        build(node << 1 | 1, mid + 1, r, nums);
        pushUp(node);
    }

    private void update(int node, int l, int r, int pos, int val) {
        if (l == r) {
            setLeaf(node, val);
            return;
        }
        int mid = (l + r) >> 1;
        if (pos <= mid) {
            update(node << 1, l, mid, pos, val);
        } else {
            update(node << 1 | 1, mid + 1, r, pos, val);
        }
        pushUp(node);
    }

    private int[][] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return new int[][]{{prod[node]}, cnt[node]};
        }
        int mid = (l + r) >> 1;
        if (qr <= mid) {
            return query(node << 1, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(node << 1 | 1, mid + 1, r, ql, qr);
        }
        int[][] a = query(node << 1, l, mid, ql, qr);
        int[][] b = query(node << 1 | 1, mid + 1, r, ql, qr);
        return merge(a[0][0], a[1], b[0][0], b[1]);
    }

    private int[][] merge(int leftProd, int[] lc, int rightProd, int[] rc) {
        int[] copy = lc.clone();
        for (int r = 0; r < k; r++) {
            copy[leftProd * r % k] += rc[r];
        }
        return new int[][]{{leftProd * rightProd % k}, copy};
    }

    private void pushUp(int node) {
        int[][] merged = merge(prod[node << 1], cnt[node << 1], prod[node << 1 | 1], cnt[node << 1 | 1]);
        prod[node] = merged[0][0];
        cnt[node] = merged[1];
    }

}
