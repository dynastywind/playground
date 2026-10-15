package org.lyndon.algo.string;

/**
 * LC1055
 */
public class ShortestWayFormString {

    public int shortestWay(String source, String target) {
        boolean[] exists = new boolean[26];
        int m = source.length();
        for (int i = 0; i < m; i++) {
            exists[source.charAt(i) - 'a'] = true;
        }
        int n = target.length();
        int count = 0;
        int j = 0;
        while (j < n) {
            if (!exists[target.charAt(j) - 'a']) {
                return -1;
            }
            count++;
            for (int i = 0; i < m && j < n; i++) {
                if (source.charAt(i) == target.charAt(j)) {
                    j++;
                }
            }
        }
        return count;
    }

    static void main() {
        var solution = new ShortestWayFormString();
        System.out.println(solution.shortestWay("abc", "abcbc"));
        System.out.println(solution.shortestWay("abc", "acdbc"));
        System.out.println(solution.shortestWay("xyz", "xzyxz"));
    }

}
