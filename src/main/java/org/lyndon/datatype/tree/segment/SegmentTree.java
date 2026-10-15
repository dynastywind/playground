package org.lyndon.datatype.tree.segment;

public interface SegmentTree {

    void update(int pos, int val);

    long query(int ql, int qr);

}
