package org.lyndon.algo.array;

/**
 * LC3635
 */
public class EarliestFinishTimeForLandAndWaterRidesII {

    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        return Math.min(solve(landStartTime, landDuration, waterStartTime, waterDuration), solve(waterStartTime, waterDuration, landStartTime, landDuration));
    }

    private int solve(int[] firstStart, int[] firstDur, int[] secondStart, int[] secondDur) {
        int minEnd = Integer.MAX_VALUE;
        for (int i = 0; i < firstStart.length; i++) {
            minEnd = Math.min(minEnd, firstStart[i] + firstDur[i]);
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < secondStart.length; j++) {
            int board = Math.max(minEnd, secondStart[j]);
            ans = Math.min(ans, board + secondDur[j]);
        }
        return ans;
    }

}
