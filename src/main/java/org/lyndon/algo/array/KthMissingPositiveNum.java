package org.lyndon.algo.array;

public class KthMissingPositiveNum {

    public int findKthPositive(int[] arr, int k) {
        int missing = 0;
        int prev = 0;
        for (int i = 0; i < arr.length; i++) {
            int diff = arr[i] - prev;
            if (diff == 1) {
                prev = arr[i];
                continue;
            }

            missing += diff - 1;
            if (missing >= k) {
                return arr[i] - (missing - k) - 1;
            }
            prev = arr[i];
        }
        if (missing < k) {
            return k - missing + prev;
        }
        return -1;
    }

    public static void main(String[] args) {
        var solution = new KthMissingPositiveNum();
        System.out.println(solution.findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
        System.out.println(solution.findKthPositive(new int[]{2, 3, 4, 7, 11}, 6));
        System.out.println(solution.findKthPositive(new int[]{1, 2, 3, 4}, 2));
    }

}
