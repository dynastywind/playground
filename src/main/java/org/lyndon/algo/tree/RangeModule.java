package org.lyndon.algo.tree;

import java.util.TreeMap;

/**
 * LC715
 * A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.
 * <p>
 * A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
 * <p>
 * Implement the RangeModule class:
 * <p>
 * RangeModule() Initializes the object of the data structure.
 * void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 * boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
 * void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * Output
 * [null, null, null, true, false, true]
 * <p>
 * Explanation
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
 * rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 * rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 */
public class RangeModule {

    private final TreeMap<Integer, Integer> map;

    public RangeModule() {
        this.map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        // [       [          ]
        // start   left       start's end
        if (start != null && map.get(start) >= left) {
            left = start;
        }
        // [     ]        ]
        // end   right    end's end
        if (end != null && map.get(end) > right) {
            right = map.get(end);
        }
        map.put(left, right);
        map.subMap(left, false, right, true).clear();
    }

    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        return start != null && map.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        // important: when start == end, map.get(end) needs to run first
        if (end != null && map.get(end) > right) {
            map.put(right, map.get(end));
        }
        if (start != null && map.get(start) > left) {
            map.put(start, left);
        }
        map.subMap(left, true, right, false).clear();
    }

}
