package org.lyndon.design;

/**
 * LC2166
 */
public class Bitset {

    private final boolean[] bits;
    private boolean one = true;
    private int cnt = 0;

    public Bitset(int size) {
        this.bits = new boolean[size];
    }

    public void fix(int idx) {
        if (one && bits[idx] || !one && !bits[idx]) {
            return;
        }
        bits[idx] = one;
        cnt++;
    }

    public void unfix(int idx) {
        if (one && !bits[idx] || !one && bits[idx]) {
            return;
        }
        bits[idx] = !one;
        cnt--;
    }

    public void flip() {
        one = !one;
        cnt = bits.length - cnt;
    }

    public boolean all() {
        return cnt == bits.length;
    }

    public boolean one() {
        return cnt > 0;
    }

    public int count() {
        return cnt;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean b : bits) {
            if (one && b || !one && !b) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    static void main() {
        var bitset = new Bitset(10);
        bitset.fix(1);
        bitset.flip();
        bitset.unfix(8);
        bitset.fix(1);
        System.out.println(bitset);
    }

}
