package org.lyndon.design;

import java.util.*;

public class NestedIterator implements Iterator<Integer> {

    private interface NestedInteger {
        boolean isInteger();

        Integer getInteger();

        List<NestedInteger> getList();
    }

    private final Deque<Iterator<NestedInteger>> stack;

    private Integer peeked;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public boolean hasNext() {
        if (peeked != null) {
            return true;
        }
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> top = stack.peek();
            if (!top.hasNext()) {
                stack.pop();
                continue;
            }
            NestedInteger i = top.next();
            if (i.isInteger()) {
                peeked = i.getInteger();
                return true;
            }
            stack.push(i.getList().iterator());
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer res = peeked;
        peeked = null;
        return res;
    }

}
