package org.lyndon.algo.tree;

import org.lyndon.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * LC95
 */
public class UniqueBSTII {

    public List<TreeNode> generateTrees(int n) {
        return solve(1, n);
    }

    private List<TreeNode> solve(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = solve(start, i - 1);
            List<TreeNode> rights = solve(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    result.add(new TreeNode(i, left, right));
                }
            }
        }
        return result;
    }

    static void main() {
        var solution = new UniqueBSTII();
        PrintUtils.printList(solution.generateTrees(3));
    }

}
