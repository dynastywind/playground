package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC1157
 * Design a data structure that efficiently finds the majority element of a given subarray.
 * <p>
 * The majority element of a subarray is an element that occurs threshold times or more in the subarray.
 * <p>
 * Implementing the MajorityChecker class:
 * <p>
 * MajorityChecker(int[] arr) Initializes the instance of the class with the given array arr.
 * int query(int left, int right, int threshold) returns the element in the subarray arr[left...right] that occurs at least threshold times, or -1 if no such element exists.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MajorityChecker", "query", "query", "query"]
 * [[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
 * Output
 * [null, 1, -1, 2]
 * <p>
 * Explanation
 * MajorityChecker majorityChecker = new MajorityChecker([1, 1, 2, 2, 1, 1]);
 * majorityChecker.query(0, 5, 4); // return 1
 * majorityChecker.query(0, 3, 3); // return -1
 * majorityChecker.query(2, 3, 2); // return 2
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 2 * 104
 * 1 <= arr[i] <= 2 * 104
 * 0 <= left <= right < arr.length
 * threshold <= right - left + 1
 * 2 * threshold > right - left + 1
 * At most 104 calls will be made to query
 */
public class MajorityChecker {

    private final int[] candidates;
    private final int[] counts;
    private final int n;
    private final Map<Integer, List<Integer>> pos;

    public MajorityChecker(int[] arr) {
        this.n = arr.length;
        this.candidates = new int[4 * n];
        this.counts = new int[4 * n];
        this.pos = new HashMap<>();
        build(arr, 1, 0, n - 1);
        for (int i = 0; i < arr.length; i++) {
            pos.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    private void build(int[] arr, int node, int l, int r) {
        if (l == r) {
            candidates[node] = arr[l];
            counts[node] = 1;
            return;
        }
        int mid = (l + r) >>> 1;
        build(arr, node << 1, l, mid);
        build(arr, node << 1 | 1, mid + 1, r);
        int[] merged = merge(node << 1, node << 1 | 1);
        candidates[node] = merged[0];
        counts[node] = merged[1];
    }

    private int[] merge(int a, int b) {
        return merge(candidates[a], counts[a], candidates[b], counts[b]);
    }

    private int[] merge(int candA, int countA, int candB, int countB) {
        if (candA == candB) {
            return new int[]{candA, countA + countB};
        } else if (countA >= countB) {
            return new int[]{candA, countA - countB};
        } else {
            return new int[]{candB, countB - countA};
        }
    }

    public int query(int left, int right, int threshold) {
        int candidate = query(1, 0, n - 1, left, right)[0];
        List<Integer> list = pos.get(candidate);
        int cnt = upperBound(list, right) - lowerBound(list, left);
        return cnt >= threshold ? candidate : -1;
    }

    private int[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return new int[]{candidates[node], counts[node]};
        }
        int mid = (l + r) >>> 1;
        if (qr <= mid) {
            return query(node << 1, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(node << 1 | 1, mid + 1, r, ql, qr);
        }
        int[] a = query(node << 1, l, mid, ql, qr);
        int[] b = query(node << 1 | 1, mid + 1, r, ql, qr);
        return merge(a[0], a[1], b[0], b[1]);
    }

    private int lowerBound(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int upperBound(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
