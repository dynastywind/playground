package org.lyndon.algo.array;

/**
 * LC2131
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 * <p>
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
 * <p>
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 * <p>
 * A palindrome is a string that reads the same forward and backward.
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 * Example 2:
 * <p>
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 * Example 3:
 * <p>
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is "xx".
 */
public class LongestPalindromeByConcatTwoLetterWords {

    public int longestPalindrome(String[] words) {
        int[] chars = new int[26 * 26];
        int ans = 0;
        for (String word : words) {
            int a = word.charAt(0) - 'a';
            int b = word.charAt(1) - 'a';
            int reverse = b * 26 + a;
            if (chars[reverse] > 0) {
                chars[reverse]--;
                ans += 4;
            } else {
                chars[a * 26 + b]++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (chars[i * 26 + i] > 0) {
                ans += 2;
                break;
            }
        }
        return ans;
    }

}
