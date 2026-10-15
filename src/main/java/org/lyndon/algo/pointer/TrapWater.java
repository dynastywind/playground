package org.lyndon.algo.pointer;

public class TrapWater {

    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int result = 0;
        while (left < right) {
            if (leftMax < rightMax) {
                result += leftMax - height[left];
                left++;
                leftMax = Math.max(leftMax, height[left]);
            } else {
                result += rightMax - height[right];
                right--;
                rightMax = Math.max(rightMax, height[right]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var trap = new TrapWater();
        System.out.println(trap.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

}
