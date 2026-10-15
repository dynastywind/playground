package org.lyndon.design;

/**
 * LC2296
 */
public class TextEditor {

    private final StringBuilder left = new StringBuilder();
    private final StringBuilder right = new StringBuilder();

    public TextEditor() {
    }

    public void addText(String text) {
        left.append(text);
    }

    public int deleteText(int k) {
        int n = Math.min(k, left.length());
        left.setLength(left.length() - n);
        return n;
    }

    public String cursorLeft(int k) {
        while (k-- > 0 && !left.isEmpty()) {
            int last = left.length() - 1;
            right.append(left.charAt(last));
            left.setLength(last);
        }
        return left.substring(Math.max(0, left.length() - 10));
    }

    public String cursorRight(int k) {
        while (k-- > 0 && !right.isEmpty()) {
            int last = right.length() - 1;
            left.append(right.charAt(last));
            right.setLength(last);
        }
        return left.substring(Math.max(0, left.length() - 10));
    }

}
