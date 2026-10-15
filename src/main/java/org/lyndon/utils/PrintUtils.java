package org.lyndon.utils;

import java.util.List;

public enum PrintUtils {
    ;

    public static <T> void printLists(List<List<T>> lists) {
        for (List<T> list : lists) {
            for (T t : list) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    public static <T> void printList(List<T> list) {
        for (T t : list) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static <T> void printArray(T[] arr) {
        for (T t : arr) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static void printArray(int[] arr) {
        for (int t : arr) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static void printArray(int[][] arr) {
        for (int[] a : arr) {
            printArray(a);
        }
        System.out.println();
    }

    public static void printCharMatrix(char[][] matrix) {
        StringBuffer sb = new StringBuffer();
        for (char[] chars : matrix) {
            for (int j = 0; j < chars.length; j++) {
                sb.append(chars[j]);
                if (j != chars.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
