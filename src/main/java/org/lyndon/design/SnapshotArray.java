package org.lyndon.design;

import java.util.TreeMap;

/**
 * LC1146
 * Implement a SnapshotArray that supports the following interface:
 * <p>
 * SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * <p>
 * Example 1:
 * <p>
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 */
public class SnapshotArray {

    private final TreeMap<Integer, Integer>[] array;
    private int snapshotId;

    public SnapshotArray(int length) {
        this.array = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            array[i] = new TreeMap<>();
            array[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        array[index].put(snapshotId, val);
    }

    public int snap() {
        return snapshotId++;
    }

    public int get(int index, int snap_id) {
        return array[index].floorEntry(snap_id).getValue();
    }

}
