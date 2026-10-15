package org.lyndon.algo.string;

/**
 * LC443
 */
public class StringCompression {

    public int compress(char[] chars) {
        int end = 1;
        int i = 1;
        int cnt = 1;
        while (i < chars.length) {
            if (chars[i - 1] == chars[i]) {
                cnt++;
            } else {
                if (cnt > 1) {
                    char[] cs = String.valueOf(cnt).toCharArray();
                    for (char c : cs) {
                        chars[end++] = c;
                    }
                }
                cnt = 1;
                chars[end++] = chars[i];
            }
            i++;
        }
        if (cnt > 1) {
            char[] cs = String.valueOf(cnt).toCharArray();
            for (char c : cs) {
                chars[end++] = c;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        var solution = new StringCompression();
        System.out.println(solution.compress("aabbccc".toCharArray()));
        System.out.println(solution.compress("a".toCharArray()));
        System.out.println(solution.compress("ab".toCharArray()));
        System.out.println(solution.compress("abbbbbbbbbbbb".toCharArray()));
    }

}
