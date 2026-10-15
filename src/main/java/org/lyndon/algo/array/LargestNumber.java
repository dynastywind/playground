package org.lyndon.algo.array;

import java.util.Arrays;

/**
 * LC179
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        var list = Arrays.stream(nums).mapToObj(String::valueOf).sorted((a, b) -> (a + b).compareTo(b + a)).toList();
        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i));
        }
        String result = sb.toString();
        return result.charAt(0) == '0' ? "0" : result;
    }

    public static void main(String[] args) {
        var solution = new LargestNumber();
        System.out.println(solution.largestNumber(new int[]{10, 2}));
        System.out.println(solution.largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(solution.largestNumber(new int[]{1311, 13}));
        System.out.println(solution.largestNumber(new int[]{34323, 3432}));
    }

}
