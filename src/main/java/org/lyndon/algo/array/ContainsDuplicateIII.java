package org.lyndon.algo.array;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (indexDiff <= 0 || valueDiff < 0) {
            return false;
        }
        int n = nums.length;
        if (n < 2) {
            return false;
        }

        Map<Long, Long> buckets = new HashMap<>();
        long w = valueDiff + 1;
        for (int i = 0; i < n; i++) {
            long x = nums[i];
            long id = getBucketId(x, w);
            if (buckets.containsKey(id)) {
                return true;
            }
            if (buckets.containsKey(id - 1) && x - buckets.get(id - 1) <= valueDiff) {
                return true;
            }
            if (buckets.containsKey(id + 1) && buckets.get(id + 1) - x <= valueDiff) {
                return true;
            }
            buckets.put(id, x);
            if (i >= indexDiff) {
                buckets.remove(getBucketId(nums[i - indexDiff], w));
            }
        }
        return false;
    }

    private long getBucketId(long x, long w) {
        return Math.floorDiv(x, w);
    }

}
