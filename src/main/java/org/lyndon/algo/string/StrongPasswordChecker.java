package org.lyndon.algo.string;

/**
 * LC420
 */
public class StrongPasswordChecker {

    public int strongPasswordChecker(String password) {
        int n = password.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        int missing = (hasLower ? 0 : 1) + (hasUpper ? 0 : 1) + (hasDigit ? 0 : 1);

        if (n < 6) {
            return Math.max(missing, 6 - n);
        }

        int replace = 0;
        int[] cnt = new int[3];
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && password.charAt(j) == password.charAt(i)) {
                j++;
            }
            int len = j - i;
            if (len >= 3) {
                replace += len / 3;
                cnt[len % 3]++;
            }
            i = j;
        }

        if (n <= 20) {
            return Math.max(missing, replace);
        }

        int delete = n - 20;
        int remain = delete;
        int use = Math.min(remain, cnt[0]);
        replace -= use;
        remain -= use;

        use = Math.min(remain / 2, cnt[1]);
        replace -= use;
        remain -= use * 2;

        replace -= remain / 3;
        return delete + Math.max(missing, replace);
    }

}
