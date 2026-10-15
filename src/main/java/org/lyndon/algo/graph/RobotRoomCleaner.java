package org.lyndon.algo.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * LC489
 * You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.
 * <p>
 * The robot starts at an unknown location in the room that is guaranteed to be empty, and you do not have access to the grid, but you can move the robot using the given API Robot.
 * <p>
 * You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
 * <p>
 * When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.
 */
public class RobotRoomCleaner {

    private interface Robot {
        boolean move();

        void turnRight();

        void clean();
    }

    // clockwise
    private static final int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private final Set<Long> visited = new HashSet<>();
    private Robot robot;

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        dfs(0, 0, 0);
    }

    private void dfs(int r, int c, int d) {
        robot.clean();
        visited.add(key(r, c));

        for (int i = 0; i < 4; i++) {
            int nd = (d + i) % 4;
            int nr = r + dirs[nd][0];
            int nc = c + dirs[nd][1];

            if (!visited.contains(key(nr, nc)) && robot.move()) {
                dfs(nr, nc, nd);
                goBack();
            }
            robot.turnRight();
        }
    }

    private long key(int r, int c) {
        return ((long) (r + 1000) << 32) | (c + 1000);
    }

    private void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

}
