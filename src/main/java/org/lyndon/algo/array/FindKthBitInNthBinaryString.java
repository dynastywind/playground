package org.lyndon.algo.array;

/**
 * LC1545
 */
public class FindKthBitInNthBinaryString {

    public char findKthBit(int n, int k) {
        boolean invert = false;
        while (n > 1) {
            int mid = 1 << (n - 1);
            if (k == mid) {
                return invert ? '0' : '1';
            }
            if (k > mid) {
                k = (1 << n) - k;
                invert = !invert;
            }
            n--;
        }
        return invert ? '1' : '0';
    }

}
