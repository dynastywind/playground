package org.lyndon.algo.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC737
 */
public class SentenceSimilarityII {

    private static class UnionFind {
        final Map<String, String> parent;

        public UnionFind() {
            this.parent = new HashMap<>();
        }

        public String find(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                return x;
            }
            if (!x.equals(parent.get(x))) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(String a, String b) {
            String ra = find(a);
            String rb = find(b);
            if (ra.equals(rb)) {
                return;
            }
            parent.put(rb, ra);
        }
    }

    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        int m = sentence1.length;
        int n = sentence2.length;
        if (m != n) {
            return false;
        }
        UnionFind uf = new UnionFind();
        for (List<String> pair : similarPairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        for (int i = 0; i < m; i++) {
            String a = sentence1[i];
            String b = sentence2[i];
            String ra = uf.find(a);
            String rb = uf.find(b);
            if (!ra.equals(rb)) {
                return false;
            }
        }
        return true;
    }

}
