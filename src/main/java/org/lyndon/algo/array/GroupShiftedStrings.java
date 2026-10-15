package org.lyndon.algo.array;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC249
 */
public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String string : strings) {
            encode(string, groups);
        }
        return new ArrayList<>(groups.values());
    }

    private void encode(String s, Map<String, List<String>> groups) {
        if (s.startsWith("a")) {
            groups.computeIfAbsent(s, k -> new ArrayList<>()).add(s);
            return;
        }
        int diff = s.charAt(0) - 'a';
        StringBuilder sb = new StringBuilder();
        sb.append('a');
        for (int i = 1; i < s.length(); i++) {
            int d = s.charAt(i) - 'a';
            sb.append((char) (Math.floorMod(d - diff, 26) + 'a'));
        }
        groups.computeIfAbsent(sb.toString(), k -> new ArrayList<>()).add(s);
    }

    static void main() {
        var solution = new GroupShiftedStrings();
        PrintUtils.printLists(solution.groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
        PrintUtils.printLists(solution.groupStrings(new String[]{"a"}));
    }

}
