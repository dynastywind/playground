package org.lyndon.algo.array;

/**
 * LC2337
 */
public class MovePiecesToObtainAString {

    // L(s) >= L(t)
    // R(s) <= R(t)
    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0;
        int j = 0;
        while (i < n || j < n) {
            while (i < n && start.charAt(i) == '_') {
                i++;
            }
            while (j < n && target.charAt(j) == '_') {
                j++;
            }
            if (i == n || j == n) {
                return i == n && j == n;
            }
            char c = start.charAt(i);
            if (c != target.charAt(j)) {
                return false;
            }
            if (c == 'L' && i < j) {
                return false;
            }
            if (c == 'R' && i > j) {
                return false;
            }
            i++;
            j++;
        }
        return i == n && j == n;
    }

}
