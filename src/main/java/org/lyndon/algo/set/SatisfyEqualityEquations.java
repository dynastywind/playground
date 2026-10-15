package org.lyndon.algo.set;

/**
 * LC990
 * You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
 * <p>
 * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input: equations = ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
 * There is no way to assign the variables to satisfy both equations.
 * Example 2:
 * <p>
 * Input: equations = ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] is a lowercase letter.
 * equations[i][1] is either '=' or '!'.
 * equations[i][2] is '='.
 * equations[i][3] is a lowercase letter.
 */
public class SatisfyEqualityEquations {

    private static class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        public DisjointSet(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            int root = x;
            while (root != parent[root]) {
                root = parent[root];
            }
            while (x != root) {
                int tmp = parent[x];
                parent[x] = root;
                x = tmp;
            }
            return root;
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return;
            }
            if (rank[ra] > rank[rb]) {
                int tmp = ra;
                ra = rb;
                rb = tmp;
            }
            parent[ra] = rb;
            if (rank[ra] == rank[rb]) {
                rank[rb]++;
            }
        }
    }

    public boolean equationsPossible(String[] equations) {
        DisjointSet set = new DisjointSet(26);
        for (String equation : equations) {
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if (equation.charAt(1) == '=') {
                set.union(a - 'a', b - 'a');
            }
        }
        for (String equation : equations) {
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if (equation.charAt(1) == '!') {
                if (set.find(a - 'a') == set.find(b - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }

}
