package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * For every contiguous window of a fixed size, remove the largest k values by multiplicity and compute the mean of the remaining values. Process sliding updates incrementally rather than sorting each complete window again.
 * <p>
 * Implement trimmed_window_means(nums: int[], window: int, k: int) -> string[]. Return means in window order as reduced fractions numerator/denominator, using a positive denominator. This exact fraction encoding is an explicit practice representation that avoids cross-language floating-point rounding differences.
 * <p>
 * Constraints & Assumptions
 * 1 <= len(nums) <= 200000, 1 <= window <= len(nums), and 0 <= k < window.
 * Values range from -1,000,000 through 1,000,000. Use a sufficiently wide integer for window sums.
 * Remove exactly k occurrences, not k distinct values. Equal values on the partition boundary may be represented in either group as long as the multiset counts are correct.
 * For k == 0, retain the whole window. A window that would retain zero values is excluded by the input contract.
 * Reduce each fraction by the greatest common divisor of the absolute numerator and denominator. Zero is 0/1; an integer mean such as 5 is 5/1.
 * Aim for O(n log window) processing with O(window) maintained state, apart from the returned results.
 * <p>
 * Examples:
 * nums = [1,9,2,8], window = 3, k = 1
 * result = ["3/2","5/1"]
 * nums = [-1,1,3], window = 2, k = 0
 * result = ["0/1","2/1"]
 */
public class SlidingMeansAfterRemovingLargestValues {

    private final TreeMap<Integer, Integer> top = new TreeMap<>();
    private int topSize;
    private final TreeMap<Integer, Integer> rest = new TreeMap<>();
    private long restSum;

    public List<String> trimmed_window_means(List<Number> nums, int window, int k) {
        reset();

        List<String> res = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i).intValue();
            top.merge(num, 1, Integer::sum);
            topSize++;
            if (i >= window) {
                int victim = nums.get(i - window).intValue();
                if (top.containsKey(victim)) {
                    if (top.merge(victim, -1, Integer::sum) == 0) {
                        top.remove(victim);
                    }
                    topSize--;
                } else {
                    if (rest.merge(victim, -1, Integer::sum) == 0) {
                        rest.remove(victim);
                    }
                    restSum -= victim;
                }
            }
            rebalance(k);
            if (i >= window - 1) {
                res.add(frac(restSum, window - k));
            }
        }
        return res;
    }

    private void rebalance(int k) {
        while (topSize > k) {
            int key = top.firstKey();
            if (top.merge(key, -1, Integer::sum) == 0) {
                top.remove(key);
            }
            topSize--;
            rest.merge(key, 1, Integer::sum);
            restSum += key;
        }
        while (topSize < k && !rest.isEmpty()) {
            int key = rest.lastKey();
            if (rest.merge(key, -1, Integer::sum) == 0) {
                rest.remove(key);
            }
            restSum -= key;
            top.merge(key, 1, Integer::sum);
            topSize++;
        }
        while (!top.isEmpty() && !rest.isEmpty() && top.firstKey() < rest.lastKey()) {
            int lo = top.firstKey();
            int hi = rest.lastKey();
            if (top.merge(lo, -1, Integer::sum) == 0) {
                top.remove(lo);
            }
            rest.merge(lo, 1, Integer::sum);
            restSum += lo;
            if (rest.merge(hi, -1, Integer::sum) == 0) {
                rest.remove(hi);
            }
            restSum -= hi;
            top.merge(hi, 1, Integer::sum);
        }
    }

    private String frac(long p, long q) {
        long g = gcd(Math.abs(p), q);
        return (p / g) + "/" + (q / g);
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private void reset() {
        top.clear();
        topSize = 0;
        rest.clear();
        restSum = 0;
    }

    static void main() {
        var solution = new SlidingMeansAfterRemovingLargestValues();
        PrintUtils.printList(solution.trimmed_window_means(List.of(5, 9, 10, 4), 3, 2));
    }

}
