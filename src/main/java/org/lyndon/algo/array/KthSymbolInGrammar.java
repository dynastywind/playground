package org.lyndon.algo.array;

/**
 * LC779
 */
public class KthSymbolInGrammar {

    // Integer.bitCount(k - 1) & 1
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        int half = 1 << (n - 2);
        if (k <= half) {
            return kthGrammar(n - 1, k);
        }
        return 1 - kthGrammar(n - 1, k - half);
    }

}
