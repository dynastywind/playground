package org.lyndon.algo.dp.stone;

import java.util.Arrays;

/**
 * LC1686
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * There are n stones in a pile. On each player's turn, they can remove a stone from the pile and receive points based on the stone's value. Alice and Bob may value the stones differently.
 * <p>
 * You are given two integer arrays of length n, aliceValues and bobValues. Each aliceValues[i] and bobValues[i] represents how Alice and Bob, respectively, value the ith stone.
 * <p>
 * The winner is the person with the most points after all the stones are chosen. If both players have the same amount of points, the game results in a draw. Both players will play optimally. Both players know the other's values.
 * <p>
 * Determine the result of the game, and:
 * <p>
 * If Alice wins, return 1.
 * If Bob wins, return -1.
 * If the game results in a draw, return 0.
 * <p>
 * Example 1:
 * <p>
 * Input: aliceValues = [1,3], bobValues = [2,1]
 * Output: 1
 * Explanation:
 * If Alice takes stone 1 (0-indexed) first, Alice will receive 3 points.
 * Bob can only choose stone 0, and will only receive 2 points.
 * Alice wins.
 * Example 2:
 * <p>
 * Input: aliceValues = [1,2], bobValues = [3,1]
 * Output: 0
 * Explanation:
 * If Alice takes stone 0, and Bob takes stone 1, they will both have 1 point.
 * Draw.
 * Example 3:
 * <p>
 * Input: aliceValues = [2,4,3], bobValues = [1,6,7]
 * Output: -1
 * Explanation:
 * Regardless of how Alice plays, Bob will be able to have more points than Alice.
 * For example, if Alice takes stone 1, Bob can take stone 2, and Alice takes stone 0, Alice will have 6 points to Bob's 7.
 * Bob wins.
 */
public class StoneGameVI {

    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int[] total = new int[aliceValues.length];
        Integer[] pos = new Integer[aliceValues.length];
        for (int i = 0; i < aliceValues.length; i++) {
            total[i] = aliceValues[i] + bobValues[i];
            pos[i] = i;
        }
        Arrays.sort(pos, (a, b) -> Integer.compare(total[b], total[a]));
        int alice = 0;
        int bob = 0;
        int i = 0;
        for (; i < pos.length - 1; i += 2) {
            alice += aliceValues[pos[i]];
            bob += bobValues[pos[i + 1]];
        }
        if (aliceValues.length % 2 == 1) {
            alice += aliceValues[pos[i]];
        }
        return Integer.compare(alice, bob);
    }

    public static void main(String[] args) {
        var solution = new StoneGameVI();
        System.out.println(solution.stoneGameVI(new int[]{40, 76, 27, 31, 40, 12, 57, 10, 88, 72, 85, 5, 28, 25, 61, 82, 16, 63, 50, 90, 20, 55, 63},
                new int[]{74, 5, 37, 21, 29, 59, 94, 25, 31, 10, 86, 31, 99, 45, 77, 91, 44, 73, 83, 67, 55, 12, 35}));
    }

}
