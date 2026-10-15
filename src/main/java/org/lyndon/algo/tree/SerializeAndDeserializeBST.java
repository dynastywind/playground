package org.lyndon.algo.tree;

/**
 * LC449
 */
public class SerializeAndDeserializeBST {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        if (!sb.isEmpty()) {
            sb.append(',');
        }
        sb.append(node.val);
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] parts = data.split(",");
        int[] vals = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            vals[i] = Integer.parseInt(parts[i]);
        }
        return build(vals, 0, vals.length - 1);
    }

    private TreeNode build(int[] vals, int l, int r) {
        if (l > r) {
            return null;
        }
        TreeNode node = new TreeNode(vals[l]);
        int m = l + 1;
        while (m <= r && vals[m] < vals[l]) {
            m++;
        }
        node.left = build(vals, l + 1, m - 1);
        node.right = build(vals, m, r);
        return node;
    }

}
