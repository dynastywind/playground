package org.lyndon.algo.tree;

import java.util.ArrayList;
import java.util.List;

public class FibonacciTree {

    public List<Long> solution(long k, long a, long b) {
        List<Long> pa = rootToNode(k, a);
        List<Long> pb = rootToNode(k, b);

        int i = 0;
        while (i < pa.size() && i < pb.size()
                && pa.get(i).longValue() == pb.get(i).longValue()) {
            i++;
        }
        int lca = i - 1;

        List<Long> ans = new ArrayList<>(pa.size() + pb.size());
        for (int t = pa.size() - 1; t >= lca; t--) ans.add(pa.get(t)); // a → LCA
        for (int t = lca + 1; t < pb.size(); t++) ans.add(pb.get(t));  // LCA → b（不重复 LCA）
        return ans;
    }

//    private long[] buildTree(long k) {
//        long[] size = new long[Math.max(k, 2) + 1];
//        size[1] = 1;
//        size[2] = 1;
//        for (int i = 3; i <= k; i++) {
//            size[i] = 1 + size[i - 1] + size[i - 2];
//        }
//        return size;
//    }

    private List<Long> rootToNode(long order, long target) {
        long cur, prev;                                  // size(order), size(order−1)
        if (order == 1) {
            cur = 1;
            prev = 0;                           // size(0) 记作 0，叶子用不到
        } else if (order == 2) {
            cur = 1;
            prev = 1;
        } else {
            long s2 = 1, s1 = 1;                         // size(1), size(2)
            for (long t = 3; t <= order; t++) {          // O(k)，k ≤ 90，无溢出
                long s = 1 + s1 + s2;
                s2 = s1;
                s1 = s;
            }
            cur = s1;
            prev = s2;
        }

        List<Long> path = new ArrayList<>();
        long root = 1;
        while (true) {
            path.add(root);
            if (root == target) return path;
            if (order < 3) {                             // 已到叶子却没命中 ⇒ 入参非法
                throw new IllegalArgumentException("index out of range: " + target);
            }

            long leftEnd = root + prev;                  // 左子树右端点 = root + size(order−1)
            long next = cur - 1 - prev;                  // size(order−2)

            if (target <= leftEnd) {                     // 左走：target ≥ root+1 天然成立，只判上界
                root = root + 1;
                order -= 1;
                cur = prev;
                prev = next;                 // (size(o−1), size(o−2))
            } else {                                     // 右走
                root = leftEnd + 1;
                order -= 2;
                long next2 = prev - 1 - next;            // size(order−3)
                cur = next;
                prev = next2;                // (size(o−2), size(o−3))
            }
        }
    }

}
