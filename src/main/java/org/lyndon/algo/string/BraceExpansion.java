package org.lyndon.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC1087
 */
public class BraceExpansion {

    public String[] expand(String s) {
        List<char[]> groups = parse(s);
        List<String> result = new ArrayList<>();
        dfs(groups, 0, new StringBuilder(), result);
        return result.toArray(new String[0]);
    }

    private List<char[]> parse(String s) {
        List<char[]> groups = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '{') {
                int j = s.indexOf('}', i);
                String inner = s.substring(i + 1, j).replace(",", "");
                char[] cs = inner.toCharArray();
                Arrays.sort(cs);
                groups.add(cs);
                i = j + 1;
            } else {
                groups.add(new char[]{c});
                i++;
            }
        }
        return groups;
    }

    private void dfs(List<char[]> groups, int i, StringBuilder sb, List<String> result) {
        if (i == groups.size()) {
            result.add(sb.toString());
            return;
        }
        for (char c : groups.get(i)) {
            sb.append(c);
            dfs(groups, i + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
