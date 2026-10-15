package org.lyndon.algo.backtrack;

/**
 * LC60
 */
public class PermutationSequence {

    private int cnt = 0;

    public String getPermutation(int n, int k) {
        cnt = 0;
        return backtrack(n, k, new StringBuilder(), new boolean[n]);
    }

    private String backtrack(int n, int k, StringBuilder sb, boolean[] used) {
        if (sb.length() == n) {
            cnt++;
            if (cnt == k) {
                return sb.toString();
            }
            return null;
        }
        for (int i = 1; i <= n; i++) {
            if (used[i - 1]) {
                continue;
            }
            sb.append(i);
            used[i - 1] = true;
            String s = backtrack(n, k, sb, used);
            if (s != null) {
                return s;
            }
            used[i - 1] = false;
            sb.setLength(sb.length() - 1);
        }
        return null;
    }

    public static void main(String[] args) {
        var solution = new PermutationSequence();
        System.out.println(solution.getPermutation(3, 3));
        System.out.println(solution.getPermutation(4, 9));
        System.out.println(solution.getPermutation(3, 1));
    }

}
