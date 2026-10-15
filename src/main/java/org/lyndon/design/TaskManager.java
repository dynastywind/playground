package org.lyndon.design;

import java.util.*;

/**
 * LC3408
 * There is a task management system that allows users to manage their tasks, each associated with a priority. The system should efficiently handle adding, modifying, executing, and removing tasks.
 * <p>
 * Implement the TaskManager class:
 * <p>
 * TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples. Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified user with the given priority.
 * <p>
 * void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user with userId. It is guaranteed that taskId does not exist in the system.
 * <p>
 * void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority. It is guaranteed that taskId exists in the system.
 * <p>
 * void rmv(int taskId) removes the task identified by taskId from the system. It is guaranteed that taskId exists in the system.
 * <p>
 * int execTop() executes the task with the highest priority across all users. If there are multiple tasks with the same highest priority, execute the one with the highest taskId. After executing, the taskId is removed from the system. Return the userId associated with the executed task. If no tasks are available, return -1.
 * <p>
 * Note that a user may be assigned multiple tasks.
 */
public class TaskManager {

    private final TreeMap<Integer, TreeSet<Integer>> priorities;
    private final Map<Integer, int[]> tasks;

    public TaskManager(List<List<Integer>> tasks) {
        this.priorities = new TreeMap<>();
        this.tasks = new HashMap<>();
        for (List<Integer> task : tasks) {
            add(task.get(0), task.get(1), task.get(2));
        }
    }

    public void add(int userId, int taskId, int priority) {
        tasks.put(taskId, new int[]{priority, userId});
        priorities.computeIfAbsent(priority, k -> new TreeSet<>()).add(taskId);
    }

    public void edit(int taskId, int newPriority) {
        if (!tasks.containsKey(taskId)) {
            return;
        }
        int[] info = tasks.get(taskId);
        if (info[0] == newPriority) {
            return;
        }
        int userId = info[1];
        rmv(taskId);
        add(userId, taskId, newPriority);
    }

    public void rmv(int taskId) {
        if (!tasks.containsKey(taskId)) {
            return;
        }
        int priority = tasks.get(taskId)[0];
        priorities.get(priority).remove(taskId);
        if (priorities.get(priority).isEmpty()) {
            priorities.remove(priority);
        }
        tasks.remove(taskId);
    }

    public int execTop() {
        if (priorities.isEmpty()) {
            return -1;
        }
        int priority = priorities.lastKey();
        int taskId = priorities.get(priority).last();
        int userId = tasks.get(taskId)[1];
        rmv(taskId);
        return userId;
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager(List.of(List.of(1, 101, 8), List.of(2, 102, 20)));
        manager.add(4, 104, 5);
        manager.edit(102, 9);
        manager.execTop();
        manager.rmv(101);
    }

}
