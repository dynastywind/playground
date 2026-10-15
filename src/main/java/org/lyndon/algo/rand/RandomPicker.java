package org.lyndon.algo.rand;

import java.util.Random;

/**
 * LC398
 * <p>
 * Reservoir Sampling
 */
public class RandomPicker {

    private final int[] nums;

    private final Random rand;

    public RandomPicker(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int cnt = 0;
        int result = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                continue;
            }
            cnt++;
            if (rand.nextInt(cnt) == 0) {
                result = i;
            }
        }
        return result;
    }

}
