package org.lyndon.algo.string;

import java.util.Arrays;

/**
 * LC833
 */
public class FindAndReplaceInString {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int k = indices.length;
        Integer[] pos = new Integer[k];
        for (int i = 0; i < k; i++) {
            pos[i] = i;
        }
        Arrays.sort(pos, (a, b) -> Integer.compare(indices[a], indices[b]));
        int n = s.length();
        int[] match = new int[n];
        Arrays.fill(match, -1);
        for (int i = 0; i < k; i++) {
            int p = pos[i];
            if (s.startsWith(sources[p], indices[p])) {
                match[indices[p]] = p;
            }
        }

        StringBuilder sb = new StringBuilder();
        int j = 0;
        while (j < n) {
            if (match[j] != -1) {
                int op = match[j];
                sb.append(targets[op]);
                j += sources[op].length();
            } else {
                sb.append(s.charAt(j++));
            }
        }
        return sb.toString();
    }

    static void main() {
        var solution = new FindAndReplaceInString();
        System.out.println(solution.findReplaceString("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"}));
        System.out.println(solution.findReplaceString("abcd", new int[]{0, 2}, new String[]{"ab", "ce"}, new String[]{"eee", "ffff"}));
        System.out.println(solution.findReplaceString("abcd", new int[]{0, 2}, new String[]{"c", "ce"}, new String[]{"eee", "ffff"}));
        System.out.println(solution.findReplaceString("vmokgggqzp", new int[]{3, 5, 1}, new String[]{"kg", "ggq", "mo"}, new String[]{"s", "so", "bfr"}));
        System.out.println(solution.findReplaceString("abcde", new int[]{2, 2, 3}, new String[]{"cde", "cdef", "dk"}, new String[]{"fe", "f", "xyz"}));
    }

}
