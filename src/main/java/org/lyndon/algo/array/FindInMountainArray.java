package org.lyndon.algo.array;

public class FindInMountainArray {

    private interface MountainArray {
        int get(int index);
        int length();
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int lo = 0;
        int hi = n - 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int peak = lo;

        int index = binarySearch(mountainArr, target, 0, peak, true);
        if (index != -1) {
            return index;
        }
        return binarySearch(mountainArr, target, peak + 1, n - 1, false);
    }

    private int binarySearch(MountainArray arr, int target, int lo, int hi, boolean asc) {
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            int val = arr.get(mid);
            if (val == target) {
                return mid;
            }
            if ((val < target) == asc) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

}
