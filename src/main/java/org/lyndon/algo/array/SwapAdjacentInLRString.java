package org.lyndon.algo.array;

/**
 * LC777
 */
public class SwapAdjacentInLRString {

    public boolean canTransform(String start, String result) {
        int n = start.length();
        int i = 0;
        int j = 0;
        while (i < n || j < n) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && result.charAt(j) == 'X') {
                j++;
            }
            if (i == n || j == n) {
                return i == n && j == n;
            }
            char c = start.charAt(i);
            if (result.charAt(j) != c) {
                return false;
            }
            if (c == 'L' && i < j) {
                return false;
            } else if (c == 'R' && i > j) {
                return false;
            }
            i++;
            j++;
        }
        return i == n && j == n;
    }

    public static void main(String[] args) {
        var solution = new SwapAdjacentInLRString();
        System.out.println(solution.canTransform("RXXLRXRXL", "XRLXXRRLX"));
        System.out.println(solution.canTransform("X", "L"));
        System.out.println(solution.canTransform("XR", "RX"));
        System.out.println(solution.canTransform("XXXXXLXXXLXXXX", "XXLXXXXXXXXLXX"));
        System.out.println(solution.canTransform("XLXRRXXRXX", "LXXXXXXRRR"));
    }

}
