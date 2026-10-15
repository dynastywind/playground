package org.lyndon.design;

/**
 * LC2241
 */
public class ATM {

    private static final int[] NOTES = {20, 50, 100, 200, 500};

    private final int[] cnts;

    public ATM() {
        this.cnts = new int[NOTES.length];
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++) {
            cnts[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int rem = amount;
        int[] ans = new int[cnts.length];
        for (int i = NOTES.length - 1; i >= 0; i--) {
            if (cnts[i] <= 0 || NOTES[i] > rem) {
                continue;
            }
            int used = Math.min(rem / NOTES[i], cnts[i]);
            ans[i] = used;
            cnts[i] -= used;
            rem -= used * NOTES[i];
            if (rem == 0) {
                return ans;
            }
        }
        for (int i = 0; i < NOTES.length; i++) {
            cnts[i] += ans[i];
        }
        return new int[]{-1};
    }

}
