package org.lyndon.algo.stack;

import java.util.Arrays;

/**
 * LC2454
 */
public class NextGreaterElementIV {

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] s1 = new int[n];
        int top1 = 0;
        int[] s2 = new int[n];
        int top2 = 0;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (top2 > 0 && nums[s2[top2 - 1]] < x) {
                ans[s2[--top2]] = x;
            }
            int move = top1;
            while (move > 0 && nums[s1[move - 1]] < x) {
                move--;
            }
            for (int k = move; k < top1; k++) {
                s2[top2++] = s1[k];
            }
            top1 = move;
            s1[top1++] = i;
        }
        return ans;
    }

}
