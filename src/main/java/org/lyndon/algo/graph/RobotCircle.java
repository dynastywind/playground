package org.lyndon.algo.graph;

/**
 * LC1041
 */
public class RobotCircle {

    /**
     *
     * @param instructions GLR
     * @return if bounded
     */
    private static boolean isRobotBounded(String instructions) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int size = dx.length;
        int[] pos = {0, 0};
        int[] dir = {0, 0};
        for (int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            switch (c) {
                case 'L':
                    dir[0] = (dir[0] - 1 + size) % size;
                    dir[1] = (dir[1] - 1 + size) % size;
                    break;
                case 'R':
                    dir[0] = (dir[0] + 1) % size;
                    dir[1] = (dir[1] + 1) % size;
                    break;
                default:
                    pos[0] += dx[dir[0]];
                    pos[1] += dy[dir[1]];
            }
        }
        if (pos[0] == 0 && pos[1] == 0) {
            return true;
        }
        return dx[dir[0]] != dx[0] || dy[dir[1]] != dy[0];
    }

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GL"));
    }

}
