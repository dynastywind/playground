package org.lyndon.algo.array;

/**
 * LC 2207
 * You are given a 0-indexed string text and another 0-indexed string pattern of length 2, both of which consist of only lowercase English letters.
 * <p>
 * You can add either pattern[0] or pattern[1] anywhere in text exactly once. Note that the character can be added even at the beginning or at the end of text.
 * <p>
 * Return the maximum number of times pattern can occur as a subsequence of the modified text.
 * <p>
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text = "abdcdbc", pattern = "ac"
 * Output: 4
 * Explanation:
 * If we add pattern[0] = 'a' in between text[1] and text[2], we get "abadcdbc". Now, the number of times "ac" occurs as a subsequence is 4.
 * Some other strings which have 4 subsequences "ac" after adding a character to text are "aabdcdbc" and "abdacdbc".
 * However, strings such as "abdcadbc", "abdccdbc", and "abdcdbcc", although obtainable, have only 3 subsequences "ac" and are thus suboptimal.
 * It can be shown that it is not possible to get more than 4 subsequences "ac" by adding only one character.
 * Example 2:
 * <p>
 * Input: text = "aabb", pattern = "ab"
 * Output: 6
 * Explanation:
 * Some of the strings which can be obtained from text and have 6 subsequences "ab" are "aaabb", "aaabb", and "aabbb".
 */
public class MaxNumberOfSubsequencesInString {

    public long maximumSubsequenceCount(String text, String pattern) {
        char c1 = pattern.charAt(0);
        char c2 = pattern.charAt(1);
        long cnt1 = 0;
        long cnt2 = 0;
        long total = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == c2) {
                total += cnt1;
                cnt2++;
            }
            if (c == c1) {
                cnt1++;
            }
        }
        return total + Math.max(cnt1, cnt2);
    }

    public static void main(String[] args) {
        var solution = new MaxNumberOfSubsequencesInString();
        System.out.println(solution.maximumSubsequenceCount("abdcdbc", "ac"));
        System.out.println(solution.maximumSubsequenceCount("aabb", "ab"));
        System.out.println(solution.maximumSubsequenceCount("aabb", "aa"));
        System.out.println(solution.maximumSubsequenceCount("aabb", "bb"));
    }

}
