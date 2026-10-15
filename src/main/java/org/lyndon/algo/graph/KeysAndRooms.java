package org.lyndon.algo.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LC841
 */
public class KeysAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        dfs(0, rooms, visited);

        for (int i = 0; i < rooms.size(); i++) {
            if (!visited.contains(i)) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int room, List<List<Integer>> rooms, Set<Integer> visited) {
        if (visited.contains(room)) {
            return;
        }
        visited.add(room);
        for (int next : rooms.get(room)) {
            dfs(next, rooms, visited);
        }
    }

}
