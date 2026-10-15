package org.lyndon.algo.array;

/**
 * LC2447
 */
public class SubarraysWithGCDEqualToK {

    public int subarrayGCD(int[] nums, int k) {
        int ans = 0;
        for (int j = 0; j < nums.length; j++) {
            int x = nums[j];
            if (x == k) {
                ans++;
            }
            for (int i = j - 1; i >= 0; i--) {
                int gcd = gcd(nums[i], nums[i + 1]);
                nums[i] = gcd;
                if (gcd == k) {
                    ans++;
                }
                if (gcd < k) {
                    break;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static void main() {
        var solution = new SubarraysWithGCDEqualToK();
        System.out.println(solution.subarrayGCD(new int[]{4, 10, 5}, 1));
    }

}
