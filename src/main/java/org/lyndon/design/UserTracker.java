package org.lyndon.design;

import java.util.*;

public class UserTracker {

    private static class Bucket {
        final int cnt;
        final LinkedHashSet<Integer> users;
        Bucket prev, next;

        Bucket(int cnt) {
            this.cnt = cnt;
            this.users = new LinkedHashSet<>();
        }
    }

    private final Map<Integer, Set<Integer>> contacts = new HashMap<>();
    private final Map<Integer, Bucket> cntToBucket = new HashMap<>();
    private final Bucket head = new Bucket(0), tail = new Bucket(Integer.MAX_VALUE);

    {
        head.next = tail;
        tail.prev = head;
    }

    public void register(int a, int b) {
        if (a == b) {
            return;
        }
        if (!contacts.computeIfAbsent(a, k -> new HashSet<>()).add(b)) {
            return;
        }
        contacts.computeIfAbsent(b, k -> new HashSet<>()).add(a);
        inc(a);
        inc(b);
    }

    private void inc(int u) {
        Bucket cur = cntToBucket.getOrDefault(u, head);
        Bucket next = cur.next;
        if (next.cnt != cur.cnt + 1) {
            Bucket nxt = new Bucket(cur.cnt + 1);
            nxt.prev = cur;
            nxt.next = next;
            cur.next = nxt;
            next.prev = nxt;
            next = nxt;
        }
        next.users.add(u);
        cntToBucket.put(u, next);
        if (cur != head) {
            cur.users.remove(u);
            if (cur.users.isEmpty()) {
                cur.prev = cur.next;
                cur.next.prev = cur.prev;
            }
        }
    }

    public int mostActiveUser() {
        Bucket top = tail.prev;
        return top == head ? -1 : top.users.getFirst();
    }

    public List<Integer> topK(int k) {
        List<Integer> res = new ArrayList<>();
        for (Bucket bucket = tail.prev; bucket != head && res.size() < k; bucket = bucket.prev) {
            for (int u : bucket.users) {
                res.add(u);
                if (res.size() == k) {
                    break;
                }
            }
        }
        return res;
    }

}
