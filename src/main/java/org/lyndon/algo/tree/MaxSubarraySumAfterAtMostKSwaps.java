package org.lyndon.algo.tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LC3962
 */
public class MaxSubarraySumAfterAtMostKSwaps {

    private int[] nums;
    private PriorityQueue<Integer> lo, hi, big, rest;
    private long sumLo, sumBig;
    private int bigSize, restSize;
    private boolean[] inBig, removed;

    public long maxSum(int[] nums, int k) {
        this.nums = nums;
        int n = nums.length;
        Comparator<Integer> asc = (x, y) -> nums[x] - nums[y];
        Comparator<Integer> desc = (x, y) -> nums[y] - nums[x];
        lo = new PriorityQueue<>(desc);
        hi = new PriorityQueue<>(asc);
        big = new PriorityQueue<>(asc);
        rest = new PriorityQueue<>(desc);
        inBig = new boolean[n];
        removed = new boolean[n];

        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            lo.clear();
            hi.clear();
            big.clear();
            rest.clear();
            sumLo = 0;
            sumBig = 0;
            bigSize = 0;
            restSize = n;
            Arrays.fill(inBig, false);
            Arrays.fill(removed, false);
            for (int j = 0; j < n; j++) {
                rest.add(j);
            }

            int t = 0;
            int nIn = 0, nOut = n;
            long inSum = 0;
            for (int j = i; j < n; j++) {
                addIn(j);
                nIn++;
                delOut(j);
                nOut--;
                inSum += nums[j];

                int cap = Math.min(k, Math.min(nIn, nOut));
                if (t > cap) {
                    t = cap;
                }
                setT(t);
                while (t > 0 && nums[lo.peek()] >= nums[bigPeek()]) {
                    t--;
                    setT(t);
                }
                while (t + 1 <= cap && nums[hi.peek()] < nums[restPeek()]) {
                    t++;
                    setT(t);
                }

                long cur = inSum - sumLo + sumBig;
                if (cur > ans) {
                    ans = cur;
                }
            }
        }
        return ans;
    }

    private void setT(int t) {
        while (lo.size() < t) {
            int m = hi.poll();
            lo.add(m);
            sumLo += nums[m];
        }
        while (lo.size() > t) {
            int m = lo.poll();
            sumLo -= nums[m];
            hi.add(m);
        }
        while (bigSize < t && restSize > 0) {
            int m = restPoll();
            big.add(m);
            inBig[m] = true;
            sumBig += nums[m];
            bigSize++;
            restSize--;
        }
        while (bigSize > t) {
            int m = bigPoll();
            inBig[m] = false;
            sumBig -= nums[m];
            bigSize--;
            rest.add(m);
            restSize++;
        }
    }

    private void addIn(int j) {
        lo.add(j);
        sumLo += nums[j];
        int m = lo.poll();
        sumLo -= nums[m];
        hi.add(m);
    }

    private void delOut(int j) {
        removed[j] = true;
        if (inBig[j]) {
            sumBig -= nums[j];
            bigSize--;
        } else {
            restSize--;
        }
    }

    private int bigPeek() {
        while (removed[big.peek()]) {
            big.poll();
        }
        return big.peek();
    }

    private int restPeek() {
        while (removed[rest.peek()]) {
            rest.poll();
        }
        return rest.peek();
    }

    private int bigPoll() {
        bigPeek();
        return big.poll();
    }

    private int restPoll() {
        restPeek();
        return rest.poll();
    }
}
