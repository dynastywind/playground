package org.lyndon.algo.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * LC71
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        String[] parts = path.replaceAll("/+", "/").split("/");
        if (parts.length == 0) {
            return "/";
        }
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty()) {
                continue;
            }
            if (part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(part);
        }
        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.addFirst(stack.pop());
        }
        return "/" + String.join("/", result);
    }

}
