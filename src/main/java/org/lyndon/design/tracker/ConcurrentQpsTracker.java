package org.lyndon.design.tracker;

import java.util.Arrays;

public class ConcurrentQpsTracker {

    private final Object writeLock = new Object();

    private long[] times = new long[1024];      // 严格递增的时间戳
    private long[] counts = new long[1024];      // 截至该秒的累计请求数
    private volatile int size = 0;               // ★ 已发布的记录数，读者的入口
    private long total = 0;                      // 仅写者访问

    public void record(long ts) {
        synchronized (writeLock) {
            total++;
            int n = size;
            if (n > 0 && times[n - 1] == ts) {
                counts[n - 1] = total;           // 同一秒：原地更新，size 不变
                return;                          // ★ 见下方"末尾更新"的说明
            }
            if (n == times.length) grow();
            times[n] = ts;                      // ① 先写数据
            counts[n] = total;
            size = n + 1;                        // ② 再发布 —— 顺序不能反！
        }
    }

    private void grow() {
        // 新数组先填满再替换引用；旧数组仍被在途读者安全使用
        times = Arrays.copyOf(times, times.length * 2);
        counts = Arrays.copyOf(counts, counts.length * 2);
    }

    public double getQPS(long start, long end) {
        if (end < start) throw new IllegalArgumentException();
        int n = size;                            // ★ 一次 volatile 读，锁定快照边界
        long[] t = times, c = counts;            // ★ 各读一次引用，防止中途被 grow 换掉
        long cnt = countUpTo(t, c, n, end) - countUpTo(t, c, n, start - 1);
        return (double) cnt / (end - start + 1);
    }

    private static long countUpTo(long[] t, long[] c, int n, long ts) {
        int lo = 0, hi = n - 1, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (t[mid] <= ts) {
                ans = mid;
                lo = mid + 1;
            } else hi = mid - 1;
        }
        return ans < 0 ? 0 : c[ans];
    }

}
