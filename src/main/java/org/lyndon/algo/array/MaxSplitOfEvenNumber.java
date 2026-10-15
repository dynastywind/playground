package org.lyndon.algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * LC2178
 */
public class MaxSplitOfEvenNumber {

    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if (finalSum % 2 != 0) {
            return ans;
        }

        long cur = 2;
        while (finalSum >= cur) {
            ans.add(cur);
            finalSum -= cur;
            cur += 2;
        }
        ans.add(ans.removeLast() + finalSum);
        return ans;
    }

}
