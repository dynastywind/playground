package org.lyndon.algo.string;

/**
 * LC1764
 */
public class FormArrayByConcatSubarrays {

    public boolean canChoose(int[][] groups, int[] nums) {
        int start = 0;
        for (int[] group : groups) {
            int pos = kmp(nums, start, group);
            if (pos == -1) {
                return false;
            }
            start = pos + group.length;
        }
        return true;
    }

    private int kmp(int[] text, int from, int[] pattern) {
        int m = pattern.length;
        int[] next = new int[m];
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = next[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                j++;
            }
            next[i] = j;
        }
        j = 0;
        for (int i = from; i < text.length; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = next[j - 1];
            }
            if (text[i] == pattern[j]) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

}
