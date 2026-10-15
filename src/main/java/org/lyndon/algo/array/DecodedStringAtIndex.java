package org.lyndon.algo.array;

/**
 * LC880
 */
public class DecodedStringAtIndex {

    public String decodeAtIndex(String s, int k) {
        int n = s.length();
        long size = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                size *= c - '0';
            } else {
                size++;
            }
        }

        long target = k;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            target %= size;
            if (target == 0 && Character.isLetter(c)) {
                return "" + c;
            }
            if (Character.isDigit(c)) {
                size /= c - '0';
            } else {
                size--;
            }
        }
        return "";
    }

}
