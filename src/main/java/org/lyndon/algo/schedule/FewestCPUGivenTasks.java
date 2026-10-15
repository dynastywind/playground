package org.lyndon.algo.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FewestCPUGivenTasks {

    private static List<Integer> solve() throws Exception {
        String content = IOUtils.toString(FewestCPUGivenTasks.class.getResourceAsStream("/schedule/FewestCPUGivenTasks.txt"));
        return Arrays.stream(content.split("\n")).map(row -> {
            String[] parts = row.split(",");
            List<Pair<Integer, Integer>> tasks = new LinkedList<>();
            for (int i = 0; i < parts.length; i += 2) {
                tasks.add(Pair.of(Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])));
            }
            return solveRow(tasks);
        }).collect(Collectors.toList());
    }

    private static int solveRow(List<Pair<Integer, Integer>> tasks) {
        List<Event> events = new LinkedList<>();
        tasks.forEach(task -> {
            events.add(new Event(task.getLeft(), true));
            events.add(new Event(task.getLeft() + task.getRight(), false));
        });
        events.sort(Comparator.comparing(Event::getTime).thenComparing(e -> e.isStart() ? 1 : -1));
        int result = 0, cpu = 0;
        for (int i = 0; i < events.size() - 1; i++) {
            Event e = events.get(i);
            if (e.start) {
                cpu++;
            } else {
                cpu--;
            }
            result = Math.max(result, cpu);
        }
        return result;
    }

    @AllArgsConstructor
    @Getter
    private static class Event {
        int time;
        boolean start;
    }

    public static void main(String[] args) throws Exception {
        solve().forEach(System.out::println);
//        List<Event> events = new LinkedList<>();
//        events.add(new Event(1, true));
//        events.add(new Event(1, false));
//        events.add(new Event(0, true));
//        events.sort(Comparator.comparing(Event::getTime).thenComparing(e -> e.isStart() ? 1 : -1));
//        System.out.println(events.get(0).getTime());
//        System.out.println(events.get(1).getTime());
//        System.out.println(events.get(1).isStart());
    }

}
