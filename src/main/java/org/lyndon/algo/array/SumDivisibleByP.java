package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LC1590
 */
public class SumDivisibleByP {

    public int minSubarray(int[] nums, int p) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int r = (int) (sum % p);
        if (r == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int n = nums.length;
        int pre = 0;
        int ans = n;
        for (int i = 0; i < n; i++) {
            pre = (pre + nums[i] % p) % p;
            int target = Math.floorMod(pre - r, p);
            if (map.containsKey(target)) {
                ans = Math.min(ans, i - map.get(target));
            }
            map.put(pre, i);
        }
        return ans == n ? -1 : ans;
    }

}
