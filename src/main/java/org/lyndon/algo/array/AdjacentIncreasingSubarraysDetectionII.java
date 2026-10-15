package org.lyndon.algo.array;

import java.util.List;

/**
 * LC3350
 */
public class AdjacentIncreasingSubarraysDetectionII {

    public int maxIncreasingSubarrays2(List<Integer> nums) {
        int n = nums.size();
        int ans = 0;
        int pre = 0;
        int cnt = 1;
        for (int i = 1; i <= n; i++) {
            if (i < n && nums.get(i) > nums.get(i - 1)) {
                cnt++;
            } else {
                ans = Math.max(ans, Math.max(cnt >> 1, Math.min(pre, cnt)));
                pre = cnt;
                cnt = 1;
            }
        }
        return ans;
    }

    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int lo = 1;
        int hi = n / 2;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(nums, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(List<Integer> nums, int len) {
        if (len == 1) {
            return true;
        }
        int i = 1;
        while (i + len < nums.size()) {
            int cnt = 1;
            while (i + len < nums.size() && nums.get(i - 1) < nums.get(i) && nums.get(i + len - 1) < nums.get(i + len)) {
                cnt++;
                i++;
                if (cnt == len) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
        var solution = new AdjacentIncreasingSubarraysDetectionII();
        System.out.println(solution.maxIncreasingSubarrays(List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1)));
        System.out.println(solution.maxIncreasingSubarrays(List.of(-13, -7, 0, 8, 10)));
    }

}
