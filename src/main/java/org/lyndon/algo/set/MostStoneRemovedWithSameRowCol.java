package org.lyndon.algo.set;

import java.util.HashMap;
import java.util.Map;

/**
 * LC947
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 * <p>
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * <p>
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
 * <p>
 * Example 1:
 * <p>
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 * Example 2:
 * <p>
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 * Example 3:
 * <p>
 * Input: stones = [[0,0]]
 * Output: 0
 * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 */
public class MostStoneRemovedWithSameRowCol {

    private static class DisjointSet {
        private final Map<Integer, Integer> parent;
        private final Map<Integer, Integer> rank;
        private int count;

        public DisjointSet() {
            this.parent = new HashMap<>();
            this.rank = new HashMap<>();
        }

        public void add(int x) {
            if (parent.containsKey(x)) {
                return;
            }
            parent.put(x, x);
            rank.put(x, 0);
            count++;
        }

        public int find(int x) {
            int root = x;
            while (root != parent.computeIfAbsent(root, k -> k)) {
                root = parent.get(root);
            }
            while (x != root) {
                int tmp = parent.get(x);
                parent.put(x, root);
                x = tmp;
            }
            return root;
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return;
            }
            if (rank.computeIfAbsent(ra, k -> 0) > rank.computeIfAbsent(rb, k -> 0)) {
                int tmp = ra;
                ra = rb;
                rb = tmp;
            }
            parent.put(ra, rb);
            if (rank.get(ra).equals(rank.get(rb))) {
                rank.merge(rb, 1, Integer::sum);
            }
            count--;
        }
    }

    public int removeStones(int[][] stones) {
        DisjointSet set = new DisjointSet();
        for (int[] stone : stones) {
            set.add(stone[0]);
            set.add(~stone[1]);
            set.union(stone[0], ~stone[1]);
        }
        return stones.length - set.count;
    }

    public static void main(String[] args) {
        var solution = new MostStoneRemovedWithSameRowCol();
        System.out.println(solution.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
        System.out.println(solution.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}));
    }

}
