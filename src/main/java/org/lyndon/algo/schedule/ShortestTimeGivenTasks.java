package org.lyndon.algo.schedule;

import org.apache.commons.io.IOUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ShortestTimeGivenTasks {

    private static List<Integer> solve(int usage, int cores) throws Exception {
        String content = IOUtils.toString(ShortestTimeGivenTasks.class.getResourceAsStream("/schedule/ShortestTimeGivenTasks.txt"));
        return Arrays.stream(content.split("\n")).map(row -> solveRow(Arrays.stream(row.split(",")).map(Integer::parseInt).collect(Collectors.toList()), usage, cores)).collect(Collectors.toList());
    }

    private static int solveRow(List<Integer> tasks, int usage, int cores) {
        Collections.sort(tasks);
        PriorityQueue<Integer> heap = new PriorityQueue<>(cores);
        for (int i = 0; i < cores; i++) {
            heap.offer(0);
        }
        for (int i = 0; i < tasks.size(); i++) {
            int taskStartAt = tasks.get(i);
            heap.offer(Math.max(heap.poll(), taskStartAt) + usage);
        }
        int result = -1;
        while (!heap.isEmpty()) {
            result = heap.poll();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        solve(4, 2).forEach(System.out::println);
    }

}
