package org.lyndon.algo.array;

/**
 * LC556
 */
public class NextGreaterElementIII {

    public int nextGreaterElement(int n) {
        char[] cs = Integer.toString(n).toCharArray();
        int len = cs.length;
        int i = len - 2;
        while (i >= 0 && cs[i] >= cs[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = len - 1;
        while (j >= 0 && cs[j] <= cs[i]) {
            j--;
        }
        swap(cs, i, j);
        reverse(cs, i + 1, len - 1);
        long num = Long.parseLong(new String(cs));
        return num > Integer.MAX_VALUE ? -1 : (int) num;
    }

    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(char[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

}
