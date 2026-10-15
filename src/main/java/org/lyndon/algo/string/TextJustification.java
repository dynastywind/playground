package org.lyndon.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * LC68
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int n = words.length;
        int i = 0;
        while (i < n) {
            // 分行
            int j = i;
            int lineLen = 0;
            while (j < n && lineLen + words[j].length() + j - i <= maxWidth) {
                lineLen += words[j].length();
                j++;
            }
            int cnt = j - i;

            // 排版
            StringBuilder sb = new StringBuilder();
            if (j == n || cnt == 1) {
                sb.append(words[i]);
                for (int k = i + 1; k < j; k++) {
                    sb.append(' ').append(words[k]);
                }
                while (sb.length() < maxWidth) {
                    sb.append(' ');
                }
            } else {
                int totalSpaces = maxWidth - lineLen;
                int gaps = cnt - 1;
                int base = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k == j - 1) {
                        break;
                    }
                    int sp = base + (k - i < extra ? 1 : 0);
                    sb.repeat(" ", Math.max(0, sp));
                }
            }

            res.add(sb.toString());
            i = j;
        }
        return res;
    }

}
