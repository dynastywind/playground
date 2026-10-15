package org.lyndon.algo.graph;

/**
 * LC223
 */
public class RectangleArea {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        if (bx1 < ax1) {
            return computeArea(bx1, by1, bx2, by2, ax1, ay1, ax2, ay2);
        }
        int s1 = (ax2 - ax1) * (ay2 - ay1);
        int s2 = (bx2 - bx1) * (by2 - by1);
        if (bx1 >= ax2 || ay1 >= by2 || by1 >= ay2) {
            return s1 + s2;
        }
        int left = Math.max(ax1, bx1);
        int right = Math.min(ax2, bx2);
        int bottom = Math.max(ay1, by1);
        int top = Math.min(ay2, by2);
        return s1 + s2 - (right - left) * (top - bottom);
    }

    public static void main(String[] args) {
        var solution = new RectangleArea();
        System.out.println(solution.computeArea(0, 0, 0, 0, -1, -1, 1, 1));
        System.out.println(solution.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

}
