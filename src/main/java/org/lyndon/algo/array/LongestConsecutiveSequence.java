package org.lyndon.algo.array;

import java.util.HashSet;
import java.util.Set;

/**
 * LC 128
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * Example 3:
 * <p>
 * Input: nums = [1,0,1,2]
 * Output: 3
 */
public class LongestConsecutiveSequence {

    /*
        [0,3,7,2,5,8,4,6,0,1]
        0,1,2,3,4,5,6,7,8
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int x : set) {
            if (set.contains(x - 1)) {
                continue;
            }
            int cur = x;
            while (set.contains(cur + 1)) {
                cur++;
            }
            ans = Math.max(ans, cur - x + 1);
        }
        return ans;
    }

}
