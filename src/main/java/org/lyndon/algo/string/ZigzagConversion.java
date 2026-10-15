package org.lyndon.algo.string;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LC6
 */
public class ZigzagConversion {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<List<String>> result = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            result.add(new ArrayList<>());
        }
        int round = 2 * numRows - 2;
        int vertical = numRows - 1;
        int i = 0;
        for (char ch : s.toCharArray()) {
            int rem = i % round;
            int r = rem < numRows ? rem : 2 * numRows - rem - 2;
            int c = i / round * vertical + (rem < numRows ? 0 : (rem - numRows) % (numRows - 2) + 1);
            List<String> row = result.get(r);
            while (row.size() < c) {
                row.add(" ");
            }
            row.add(ch + "");
            i++;
        }
        return result.stream().map(str -> String.join("", str)).collect(Collectors.joining("\n"));
    }

    public String convert2(String s, int numRows) {
        int n = s.length();
        if (n == 1) {
            return s;
        }
        int cycle = 2 * numRows - 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycle) {
                sb.append(s.charAt(j + i));
                if (i != 0 && i != n - 1 && j + cycle - i < n) {
                    sb.append(s.charAt(j + cycle - i));
                }
            }
        }
        return sb.toString();
    }

    static void main() {
        var solution = new ZigzagConversion();
        System.out.println(solution.convert("PAYPALISHIRING", 3));
        System.out.println(solution.convert("PAYPALISHIRING", 4));
        System.out.println(solution.convert("A", 1));
    }

}
