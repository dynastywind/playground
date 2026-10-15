package org.lyndon.design;

import java.util.ArrayList;
import java.util.List;

/**
 * LC1472
 */
public class BrowserHistory {

    private final List<String> history = new ArrayList<>();
    private int cur;
    private int last;

    public BrowserHistory(String homepage) {
        history.add(homepage);
        cur = 0;
        last = 0;
    }

    public void visit(String url) {
        cur++;
        if (cur < history.size()) {
            history.set(cur, url);
        } else {
            history.add(url);
        }
        last = cur;
    }

    public String back(int steps) {
        cur = Math.max(0, cur - steps);
        return history.get(cur);
    }

    public String forward(int steps) {
        cur = Math.min(last, cur + steps);
        return history.get(cur);
    }

}
