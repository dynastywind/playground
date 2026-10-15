package org.lyndon.algo.heap;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LC621
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.
 * <p>
 * Return the minimum number of CPU intervals required to complete all tasks.
 * <p>
 * Example 1:
 * <p>
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * <p>
 * Output: 8
 * <p>
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
 * <p>
 * After completing task A, you must wait two intervals before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have passed.
 * <p>
 * Example 2:
 * <p>
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 * <p>
 * Output: 6
 * <p>
 * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
 * <p>
 * With a cooling interval of 1, you can repeat a task after just one other task.
 * <p>
 * Example 3:
 * <p>
 * Input: tasks = ["A","A","A", "B","B","B"], n = 3
 * <p>
 * Output: 10
 * <p>
 * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
 */
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        for (char c : tasks) {
            cnt[c - 'A']++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int c : cnt) {
            if (c > 0) {
                pq.offer(c);
            }
        }
        int time = 0;
        // [remaining_counts, next_available_time]
        Queue<int[]> queue = new LinkedList<>();
        while (!pq.isEmpty() || !queue.isEmpty()) {
            if (!queue.isEmpty() && queue.peek()[1] <= time) {
                pq.offer(queue.poll()[0]);
            }
            if (!pq.isEmpty()) {
                int remaining = pq.poll() - 1;
                if (remaining > 0) {
                    queue.offer(new int[]{remaining, time + n + 1});
                }
            }
            time++;
        }
        return time;
    }

}
