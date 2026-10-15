package org.lyndon.algo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC2092
 */
public class FindAllPeopleWithSecret {

    private int[] parent;

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        parent[firstPerson] = 0;
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));
        for (int i = 0; i < meetings.length; i++) {
            int j = i;
            while (j < meetings.length && meetings[j][2] == meetings[i][2]) {
                j++;
            }
            for (int k = i; k < j; k++) {
                union(meetings[k][0], meetings[k][1]);
            }
            int root = find(0);
            for (int k = i; k < j; k++) {
                int x = meetings[k][0];
                int y = meetings[k][1];
                if (find(x) != root) {
                    parent[x] = x;
                }
                if (find(y) != root) {
                    parent[y] = y;
                }
            }
            i = j;
        }
        List<Integer> ans = new ArrayList<>();
        int root = find(0);
        for (int i = 0; i < n; i++) {
            if (find(i) == root) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) {
            return;
        }
        if (ra == find(0)) {
            parent[rb] = ra;
        } else {
            parent[ra] = rb;
        }
    }

}
