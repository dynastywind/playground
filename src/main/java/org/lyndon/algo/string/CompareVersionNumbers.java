package org.lyndon.algo.string;

/**
 * LC165
 */
public class CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0;
        while (i < v1.length && i < v2.length) {
            int a = Integer.parseInt(v1[i]);
            int b = Integer.parseInt(v2[i]);
            if (a < b) {
                return -1;
            } else if (a > b) {
                return 1;
            }
            i++;
        }
        while (i < v1.length) {
            int a = Integer.parseInt(v1[i++]);
            if (a > 0) {
                return 1;
            }
        }
        while (i < v2.length) {
            int b = Integer.parseInt(v2[i++]);
            if (b > 0) {
                return -1;
            }
        }
        return 0;
    }

}
