package org.lyndon.algo.graph;

/**
 * LC959
 */
public class RegionsCutBySlashes {

    private int[] parent;
    private int size;

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        size = n * n * 4;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        /*
            n = 2
            (0 1 2 3) (4 5 6 7) (8 9 10 11) (12 13 14 15)
            i = 0, j = 1 offset = 4
            i = 1, j = 0
            (i * n + j) * 4 = 8
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int base = (i * n + j) * 4;
                if (grid[i].charAt(j) == ' ') {
                    union(base, base + 1);
                    union(base, base + 2);
                    union(base, base + 3);
                } else if (grid[i].charAt(j) == '/') {
                    union(base, base + 1);
                    union(base + 2, base + 3);
                } else if (grid[i].charAt(j) == '\\') {
                    union(base + 1, base + 2);
                    union(base, base + 3);
                }

                if (i < n - 1) {
                    int b = ((i + 1) * n + j) * 4;
                    union(base + 3, b + 1);
                }
                if (j < n - 1) {
                    int b = (i * n + j + 1) * 4;
                    union(base + 2, b);
                }
            }
        }
        return size;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return;
        }
        parent[ra] = rb;
        size--;
    }

    static void main() {
        var solution = new RegionsCutBySlashes();
        System.out.println(solution.regionsBySlashes(new String[]{" /", "/ "}));
        System.out.println(solution.regionsBySlashes(new String[]{" /", "  "}));
        System.out.println(solution.regionsBySlashes(new String[]{"/\\", "\\/"}));
    }

}
