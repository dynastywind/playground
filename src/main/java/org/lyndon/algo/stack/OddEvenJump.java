package org.lyndon.algo.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LC975
 */
public class OddEvenJump {

    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 1;
        }

        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (a, b) -> arr[a] != arr[b] ? Integer.compare(arr[a], arr[b]) : Integer.compare(a, b));
        int[] oddJumps = build(idx, n);

        Arrays.sort(idx, (a, b) -> arr[a] != arr[b] ? Integer.compare(arr[b], arr[a]) : Integer.compare(a, b));
        int[] evenJumps = build(idx, n);

        boolean[] odd = new boolean[n];
        odd[n - 1] = true;
        boolean[] even = new boolean[n];
        even[n - 1] = true;
        int ans = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (oddJumps[i] != -1) {
                odd[i] = even[oddJumps[i]];
            }
            if (evenJumps[i] != -1) {
                even[i] = odd[evenJumps[i]];
            }
            if (odd[i]) {
                ans++;
            }
        }
        return ans;
    }

    private int[] build(Integer[] idx, int n) {
        int[] jumps = new int[n];
        Arrays.fill(jumps, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i : idx) {
            while (!stack.isEmpty() && stack.peek() < i) {
                jumps[stack.pop()] = i;
            }
            stack.push(i);
        }
        return jumps;
    }

}
