package org.lyndon.algo.meeting;

import java.util.TreeMap;

/**
 * LC729
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
 * <p>
 * A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
 * <p>
 * The event can be represented as a pair of integers startTime and endTime that represents a booking on the half-open interval [startTime, endTime), the range of real numbers x such that startTime <= x < endTime.
 * <p>
 * Implement the MyCalendar class:
 * <p>
 * MyCalendar() Initializes the calendar object.
 * boolean book(int startTime, int endTime) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * Output
 * [null, true, false, true]
 * <p>
 * Explanation
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
 * myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
 */
public class CalendarOne {

    private final TreeMap<Integer, Integer> map;

    public CalendarOne() {
        this.map = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        Integer prev = map.floorKey(startTime);
        if (prev != null && map.get(prev) > startTime) {
            return false;
        }
        Integer next = map.ceilingKey(startTime);
        if (next != null && next < endTime) {
            return false;
        }
        map.put(startTime, endTime);
        return true;
    }

    public static void main(String[] args) {
        var solution = new CalendarOne();
        solution.book(10, 20);
        solution.book(15, 25);
//        solution.book(47, 50);
//        solution.book(33, 41);
//        solution.book(39, 45);
//        solution.book(33, 42);
//        solution.book(25, 32);
//        solution.book(26, 35);
//        solution.book(19, 25);
//        solution.book(3, 8);
//        solution.book(8, 13);
        System.out.println(solution.book(18, 27));
    }

}
