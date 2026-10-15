package org.lyndon.algo.array;

/**
 * LC1456
 */
public class MaxVowelsInSubstringOfGivenLength {

    public int maxVowels(String s, int k) {
        int ans = 0;
        int i = 0;
        int n = s.length();
        int cnt = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                cnt++;
            }
            if (i >= k - 1) {
                ans = Math.max(ans, cnt);
                if (isVowel(s.charAt(i - k + 1))) {
                    cnt--;
                }
            }
            i++;
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        var solution = new MaxVowelsInSubstringOfGivenLength();
        System.out.println(solution.maxVowels("abciiidef", 3));
        System.out.println(solution.maxVowels("aeiou", 2));
        System.out.println(solution.maxVowels("leetcode", 3));
    }

}
