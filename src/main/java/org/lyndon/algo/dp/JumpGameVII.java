package org.lyndon.algo.dp;

/**
 * LC1871
 */
public class JumpGameVII {

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) != '0') {
            return false;
        }

        boolean[] dp = new boolean[n];
        dp[0] = true;
        // [i - maxJump, i - minJump]
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (i >= minJump && dp[i - minJump]) {
                cnt++;
            }
            if (i > maxJump && dp[i - maxJump - 1]) {
                cnt--;
            }
            dp[i] = cnt > 0 && s.charAt(i) == '0';
        }
        return dp[n - 1];
    }

}
