package org.lyndon.algo.array;

/**
 * LC395
 */
public class LongestSubstringWithAtLeastKRepeatingChars {

    public int longestSubstring(String s, int k) {
        return dfs(s, k, 0, s.length() - 1);
    }

    private int dfs(String s, int k, int l, int r) {
        if (r - l + 1 < k) {
            return 0;
        }
        char[] cnt = new char[26];
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }

        int ans = 0;
        int i = l;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && s.charAt(i) != split) {
                i++;
            }
            ans = Math.max(ans, dfs(s, k, start, i - 1));
        }
        return ans;
    }

}
