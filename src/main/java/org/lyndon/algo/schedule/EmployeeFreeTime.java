package org.lyndon.algo.schedule;

import java.util.LinkedList;
import java.util.List;

public class EmployeeFreeTime {

    private static class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", start, end);
        }
    }

    private static class Event {
        int time;
        boolean in;

        public Event(int time, boolean in) {
            this.time = time;
            this.in = in;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> answer = new LinkedList<>();
        List<Event> events = new LinkedList<>();
        for (List<Interval> e : schedule) {
            for (Interval interval : e) {
                events.add(new Event(interval.start, true));
                events.add(new Event(interval.end, false));
            }
        }
        events.sort((o1, o2) -> {
            int diff = o1.time - o2.time;
            if (diff != 0) {
                return diff;
            }
            // in before out because this mean some user is not available at this time
            return o1.in ? -1 : 1;
        });
        int active = 0;
        Event prev = null;
        for (Event e : events) {
            if (active == 0 && prev != null) {
                answer.add(new Interval(prev.time, e.time));
            }
            if (e.in) {
                active++;
            } else {
                active--;
            }
            prev = e;
        }
        return answer;
    }

    public static void main(String[] args) {
        var solution = new EmployeeFreeTime();
        List<Interval> ea = new LinkedList<>();
        ea.add(new Interval(1, 2));
        ea.add(new Interval(5, 6));
        List<Interval> eb = new LinkedList<>();
        eb.add(new Interval(1, 3));
        List<Interval> ec = new LinkedList<>();
        ec.add(new Interval(4, 10));
        List<List<Interval>> schedule = new LinkedList<>();
        schedule.add(ea);
        schedule.add(eb);
        schedule.add(ec);
        solution.employeeFreeTime(schedule).forEach(System.out::println);
    }

}
