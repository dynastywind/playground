package org.lyndon.algo.array;

/**
 * LC1344
 */
public class AngleBetweenHandsOfAClock {

    public double angleClock(int hour, int minutes) {
        double b = minutes * 6.0;
        double a = (hour % 12) * 30 + minutes / 2.0;
        double diff = Math.abs(a - b);
        if (diff > 180) {
            diff = 360 - diff;
        }
        return diff;
    }

}
