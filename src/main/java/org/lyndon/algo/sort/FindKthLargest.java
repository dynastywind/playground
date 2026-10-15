package org.lyndon.algo.sort;

import java.util.Random;

public class FindKthLargest {

    public String kthLargestNumber(String[] nums, int k) {
        int target = nums.length - k;
        int low = 0, high = nums.length - 1;
        Random rand = new Random();
        while (true) {
            String pivot = nums[low + rand.nextInt(high - low + 1)];
            int lt = low, i = low, gt = high;
            while (i <= gt) {
                int c = cmp(nums[i], pivot);
                if (c < 0) swap(nums, lt++, i++);
                else if (c > 0) swap(nums, i, gt--);
                else i++;
            }
            // [low,lt) < pivot   [lt,gt] == pivot   (gt,high] > pivot
            if (target < lt) high = lt - 1;
            else if (target > gt) low = gt + 1;
            else return pivot;               // 全相等时第一轮 [lt,gt] 就覆盖全区间,直接返回
        }
    }

    public String solution(String[] nums, int k) {
        int target = nums.length - k;
        int high = nums.length - 1;
        int low = 0;
        Random rand = new Random();
        while (true) {
            int lt = low, gt = high, i = low;
            int pivotIndex = low + rand.nextInt(high - low + 1);
            while (i <= gt) {
                int diff = cmp(nums[i], nums[pivotIndex]);
                if (diff < 0) {
                    swap(nums, lt++, i++);
                } else if (diff > 0) {
                    swap(nums, i, gt--);
                } else {
                    i++;
                }
            }
            if (target < lt) {
                high = lt - 1;
            } else if (target > gt) {
                low = gt + 1;
            } else {
                return nums[pivotIndex];
            }
        }
    }

    private int cmp(String a, String b) {
        return a.length() != b.length() ? Integer.compare(a.length(), b.length()) : a.compareTo(b);
    }

    private void swap(String[] nums, int i, int j) {
        String tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private boolean sort(int[] nums, int target, int lo, int hi, Random random) {
        int lt = lo;
        int gt = hi;
        int i = lo;
        int pivot = nums[lo + random.nextInt(hi - lo + 1)];
        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, i++, lt++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--);
            } else {
                i++;
            }
        }
        if (target < lt) {
            return sort(nums, target, lo, lt - 1, random);
        } else if (target > gt) {
            return sort(nums, target, gt + 1, hi, random);
        } else {
            return gt == lt;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        var solution = new FindKthLargest();
        System.out.println(solution.sort(new int[]{84, 75, 84}, 2, 0, 2, new Random()));
    }

}
