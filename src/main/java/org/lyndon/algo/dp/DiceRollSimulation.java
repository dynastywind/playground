package org.lyndon.algo.dp;

import java.util.Arrays;

/**
 * LC1223
 */
public class DiceRollSimulation {

    public int dieSimulator(int n, int[] rollMax) {
        final int MOD = 1_000_000_007;
        int[][] a = new int[n][6];
        int[][] b = new int[n][6];
        for (int i = 0; i < n; i++) {
            Arrays.fill(a[0], 1);
            Arrays.fill(b[0], 1);
        }

        for (int i = 1; i < n; i++) {
            long s = 0;
            for (int j = 0; j < 6; j++) {
                s += a[i - 1][j];
            }
            int sum = (int) (s % MOD);
            for (int j = 0; j < 6; j++) {
                int pos = i - rollMax[j];
                int sub = pos >= 0 ? b[pos][j] : 0;
                a[i][j] = Math.floorMod(sum - sub, MOD);
                b[i][j] = Math.floorMod(sum - a[i - 1][j], MOD);
            }
        }
        long ans = 0;
        for (int i = 0; i < 6; i++) {
            ans = (ans + a[n - 1][i]) % MOD;
        }
        return (int) (ans % MOD);
    }

    static void main() {
        var solution = new DiceRollSimulation();
//        System.out.println(solution.dieSimulator(2, new int[]{1, 1, 2, 2, 2, 3}));
//        System.out.println(solution.dieSimulator(2, new int[]{1, 1, 1, 1, 1, 1}));
        System.out.println(solution.dieSimulator(3, new int[]{1, 1, 1, 2, 2, 3}));
    }

}
