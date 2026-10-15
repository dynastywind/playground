package org.lyndon.algo.array;

/**
 * LC1358
 */
public class NumberOfSubstringsContainingAllThreeChars {

    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] cnt = new int[3];
        int left = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                cnt[s.charAt(left++) - 'a']--;
            }
            ans += left;
        }
        return ans;
    }

}
