package org.lyndon.algo.tree;

/**
 * LC427
 */
public class ConstructQuadTree {

    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public Node construct(int[][] grid) {
        int n = grid.length;
        return build(grid, 0, 0, n - 1, n - 1);
    }

    private Node build(int[][] grid, int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return new Node(grid[sr][sc] == 1, true);
        }
        Node topLeft = build(grid, sr, sc, (sr + er) >> 1, (sc + ec) >> 1);
        Node topRight = build(grid, sr, ((sc + ec) >> 1) + 1, (sr + er) >> 1, ec);
        Node bottomLeft = build(grid, ((sr + er) >> 1) + 1, sc, er, (sc + ec) >> 1);
        Node bottomRight = build(grid, ((sr + er) >> 1) + 1, ((sc + ec) >> 1) + 1, er, ec);
        Node node = new Node();
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf && topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {
            node.val = topLeft.val;
            node.isLeaf = true;
        } else {
            node.topLeft = topLeft;
            node.topRight = topRight;
            node.bottomLeft = bottomLeft;
            node.bottomRight = bottomRight;
        }
        return node;
    }

}
