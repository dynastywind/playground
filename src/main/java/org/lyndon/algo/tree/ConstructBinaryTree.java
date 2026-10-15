package org.lyndon.algo.tree;

import java.util.Stack;

public class ConstructBinaryTree {

    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (c == ')') {
                stack.pop();
                i++;
            } else if (c == '(') {
                i++;
            } else {
                int[] pair = findNumber(s, i);
                TreeNode node = new TreeNode();
                node.val = pair[0];
                i = pair[1];

                if (!stack.empty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left == null) {
                        parent.left = node;
                    } else {
                        parent.right = node;
                    }
                }
                stack.push(node);
            }
        }
        return stack.peek();
    }

    private int[] findNumber(String s, int start) {
        boolean negative = false;
        int number = 0;
        int index = s.length();
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '-') {
                negative = true;
            } else {
                index = i;
                break;
            }
        }
        return new int[]{negative ? -number : number, index};
    }

    public static void main(String[] args) {
        var solution = new ConstructBinaryTree();
        System.out.println(solution.str2tree("4(2(3)(1))(6(5))").bfs());
    }

}
