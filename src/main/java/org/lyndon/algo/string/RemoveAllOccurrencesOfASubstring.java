package org.lyndon.algo.string;

/**
 * LC1910
 */
public class RemoveAllOccurrencesOfASubstring {

    public String removeOccurrences(String s, String part) {
        int m = part.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (sb.length() >= m && sb.indexOf(part, sb.length() - m) == sb.length() - m) {
                sb.setLength(sb.length() - m);
            }
        }
        return sb.toString();
    }

}
